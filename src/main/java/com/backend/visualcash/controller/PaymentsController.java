/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.controller;

import com.backend.visualcash.dto.CoinpaymentsApiDto;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.brunocvcunha.coinpayments.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
            @RequestParam String status, @RequestParam String amount1,
            @RequestParam Double amount2, @RequestParam String currency1,
            @RequestParam String currency2, @RequestParam String ipn_mode,
            @RequestHeader("HMAC") String hmac, HttpServletRequest request) throws MessagingException, IOException {
        if (!ipn_mode.equals("hmac")) {
            return new ResponseEntity("IPN Mode is not HMAC.", HttpStatus.BAD_REQUEST);
        }
        if (hmac.isEmpty()) {
            return new ResponseEntity("No HMAC Signature Sent.", HttpStatus.BAD_REQUEST);
        }
        emailService.sendEmail(new EmailValuesDTO(mailFrom, "ipn-url confirmed", txn_id + ", " + status + ", " + amount1
                + ", " + amount2 + ", " + currency1 + ", " + currency2 + ", " + ipn_mode + ", " + hmac+", "+inputStreamToString(request.getInputStream())), url);
        return new ResponseEntity(txn_id, HttpStatus.BAD_REQUEST);
    }

     public String inputStreamToString(InputStream inputStream) throws IOException {
      //Creating an InputStream object
      //creating an InputStreamReader object
      InputStreamReader isReader = new InputStreamReader(inputStream);
      //Creating a BufferedReader object
      BufferedReader reader = new BufferedReader(isReader);
      StringBuffer sb = new StringBuffer();
      String str;
      while((str = reader.readLine())!= null){
         sb.append(str);
      }
      return(sb.toString());
   }
}
