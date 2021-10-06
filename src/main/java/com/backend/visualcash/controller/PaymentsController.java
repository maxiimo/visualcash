/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.controller;

import com.backend.visualcash.dto.CoinpaymentsApiDto;
import com.backend.visualcash.dto.Mensaje;
import com.backend.visualcash.email.dto.EmailValuesDTO;
import com.backend.visualcash.email.service.EmailService;
import com.backend.visualcash.entity.Paquete;
import com.backend.visualcash.entity.Payments;
import com.backend.visualcash.security.entity.Usuario;
import com.backend.visualcash.security.service.UsuarioService;
import com.backend.visualcash.service.PaquetesVisualcashService;
import com.backend.visualcash.service.PaymenService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import javax.mail.MessagingException;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.brunocvcunha.coinpayments.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Fabian
 */
@Controller
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    PaquetesVisualcashService pvcs;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PaymenService paymenService;

    @Autowired
    EmailService emailService;
    
    @Value("${spring.mail.username}")
    private String mailFrom;
    
    @Value("${url}")
    private String url;
    
    @PostMapping("/paquete-visualcash")
    public ResponseEntity<String> buyPaquete(@RequestParam String paquete, @RequestParam String to_currency, Principal principal) throws IOException {
        if (!pvcs.existsByNombre(paquete)) {
            pvcs.save(new Paquete(paquete, "PAQUETE VISUAL circular32 MORADO-01.png", "30 USD EN INVERSION/2  USD EN COINS/15 VISUALIZACIONES DIARIAS/PAGO DIARIO 0.78 USD/DIAS HABILES DE LUNES A VIERNES",
                    32.00, 20, 0.75, 15));
        }
        Optional<Paquete> paq = pvcs.getByNombre(paquete);
        Optional<Usuario> user = usuarioService.getByEmail(principal.getName());
        if (paq.isPresent() && user.isPresent()) {
            Paquete _paquete = paq.get();
            ResponseWrapper<CreateTransactionResponse> transaction
                    = new CoinpaymentsApiDto().createTransaction(to_currency, user.get().getEmail(), paq.get().getPrecio());
            if ("ok".equals(transaction.getError())) {
                CreateTransactionResponse result = transaction.getResult();
                Payments payment = new Payments(to_currency, _paquete.getPrecio(), result.getAmount(), result.getTransactionId(),
                        result.getStatusUrl(), "initialized", user.get(), paq.get());
                payment.setCreatedAt(new Date());
                paymenService.save(payment);
            }
            return new ResponseEntity(transaction.getError(), HttpStatus.OK); 
        }
        return new ResponseEntity("Ha ocurrido un error.", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/verify-pvc")
    public ResponseEntity verifyPayment(@RequestParam String txn_id,
            @RequestParam String status, @RequestParam Double amount1,
            @RequestParam Double amount2, @RequestParam Double currency1,
            @RequestParam String currency2) throws MessagingException{
        emailService.sendEmail(new EmailValuesDTO(mailFrom,"ipn-url confirmed",txn_id+", "+status+", "+amount1+
        ", "+amount2+", "+currency1+", "+currency2),url);
        return new ResponseEntity(txn_id, HttpStatus.BAD_REQUEST);
    }
    
        public String getRemoteContents(String url) throws Exception {
    URL urlObject = new URL(url);
    URLConnection conn = urlObject.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String inputLine, output = "";
    while ((inputLine = in.readLine()) != null) {
         output += inputLine;
    }   
    in.close();
        
    return output;
}
}
