/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.controller;

import com.backend.visualcash.entity.Paquete;
import com.backend.visualcash.service.PaquetesVisualcashService;
import java.io.IOException;
import java.util.List;
import org.apache.http.impl.client.HttpClients;
import org.brunocvcunha.coinpayments.CoinPayments;
import org.brunocvcunha.coinpayments.model.BasicInfoResponse;
import org.brunocvcunha.coinpayments.model.ResponseWrapper;
import org.brunocvcunha.coinpayments.requests.CoinPaymentsBasicAccountInfoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabian
 */
@RestController
@RequestMapping("/paquetesVisualcash")
@CrossOrigin(origins = "*")

public class PaqueteController {
    Logger logger = LoggerFactory.getLogger(PaqueteController.class);
    @Autowired
    PaquetesVisualcashService pvcs;
    
    @GetMapping("/list")
    public ResponseEntity<List<Paquete>> list() throws IOException{
        /*CoinPayments api = CoinPayments.builder()
        .publicKey("f5fa63a03680161eaefb6115f03709d93d03ce61e51afe8882633c2fef5aebd7")
        .privateKey("ee97e95FCE9Db29119a44A54679429198720b6f2B8a61bdBF3A72D522aC32e37")
        .client(HttpClients.createDefault()).build();

ResponseWrapper<BasicInfoResponse> accountInfo = api.sendRequest(new CoinPaymentsBasicAccountInfoRequest());
logger.error("Account: " + accountInfo.getResult());
return new ResponseEntity(accountInfo.getResult(), HttpStatus.OK);*/
        if(!pvcs.existsByNombre("Visual 32")){
            pvcs.save(new Paquete("Visual 32", "PAQUETE VISUAL circular32 MORADO-01.png", "30 USD EN INVERSION/2  USD EN COINS/15 VISUALIZACIONES DIARIAS/PAGO DIARIO 0.78 USD/DIAS HABILES DE LUNES A VIERNES",
                    32, 20, 0.75, 15));
        }
        return new ResponseEntity(pvcs.list(), HttpStatus.OK);
    }
    @DeleteMapping()
    public ResponseEntity delete(@RequestParam int id){
        pvcs.delete(id);
        return new ResponseEntity("ok",  HttpStatus.OK);
    }
}