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
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Optional;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import static javax.xml.crypto.dsig.SignatureMethod.HMAC_SHA512;
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

    private String merchant_id = "2b6cdfe8f1df05a31b7af9928cf7f56b";

    private String ipn_secret = "fvgfdft3453423ew55ret35";
    
    private final String HMAC_SHA512 = "HmacSHA512";

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
            @RequestParam String merchant, @RequestHeader("HMAC") String hmac, HttpServletRequest request) throws MessagingException, IOException, Exception {
        if (!ipn_mode.equals("hmac")) {
            return new ResponseEntity("IPN Mode is not HMAC.", HttpStatus.BAD_REQUEST);
        }
        if (hmac.isEmpty()) {
            return new ResponseEntity("No HMAC Signature Sent.", HttpStatus.BAD_REQUEST);
        }
        if (merchant.isEmpty() || !merchant.equals(this.merchant_id.trim())) {
            return new ResponseEntity("No or incorrect merchant id.", HttpStatus.BAD_REQUEST);
        }
        if (!calculateHMAC(inputStreamToString(request), ipn_secret).equals(hmac)) {
            return new ResponseEntity("HMAC signature does not match.", HttpStatus.BAD_REQUEST);
        }
        Optional <Payments> payment = paymenService.getByTxnIdAndStatus(txn_id, "initialized");
        if(payment.isPresent()){
            Payments dataPayment = payment.get();
            if (dataPayment.getTo_currency().equals(currency2)) {
                return new ResponseEntity("Currency Mismatch.", HttpStatus.BAD_REQUEST);
            }
            if (dataPayment.getAmount() != amount2) {
                return new ResponseEntity("Amount is lesser than order total.", HttpStatus.BAD_REQUEST);
            }            
            emailService.sendEmail(new EmailValuesDTO(mailFrom, "ipn-url confirmed", txn_id + ", " + status + ", " + amount1
                + ", " + amount2 + ", " + currency1 + ", " + currency2 + ", " + ipn_mode + ", " + hmac + ", " + inputStreamToString(request)+", "+dataPayment.getUsuario().getEmail()+", "+dataPayment.getPaquete().getNombre()), url);
        
        }
        return new ResponseEntity(txn_id, HttpStatus.BAD_REQUEST);
    }

    public String inputStreamToString(HttpServletRequest request) throws Exception {
        Enumeration en = request.getParameterNames();
        String str = "";
        while (en.hasMoreElements()) {
            String paramName = (String) en.nextElement();
            String paramValue = request.getParameter(paramName);
            str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue,java.nio.charset.StandardCharsets.UTF_8.toString());
        }
        if (str.length() > 0) {
            str = str.substring(1);
        }
        return str;
    }

    public String calculateHMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
        Mac mac = Mac.getInstance(HMAC_SHA512);
        mac.init(secretKeySpec);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    private String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
