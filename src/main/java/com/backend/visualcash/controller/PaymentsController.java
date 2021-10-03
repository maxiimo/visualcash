/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.controller;

import com.backend.visualcash.dto.CoinpaymentsApiDto;
import com.backend.visualcash.dto.Mensaje;
import com.backend.visualcash.entity.Paquete;
import com.backend.visualcash.entity.Payments;
import com.backend.visualcash.security.dto.UserInfo;
import com.backend.visualcash.security.entity.Usuario;
import com.backend.visualcash.security.service.UsuarioService;
import com.backend.visualcash.service.PaquetesVisualcashService;
import com.backend.visualcash.service.PaymenService;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    
    @PostMapping("/paquete-visualcash")
    public String buyPaquete(@RequestParam String paquete,@RequestParam String to_currency,Principal principal) throws IOException {
        return "ok";
    }
}
