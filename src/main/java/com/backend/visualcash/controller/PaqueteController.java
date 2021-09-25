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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabian
 */
@RestController
@RequestMapping("/paquetes")
public class PaqueteController {
    Logger logger = LoggerFactory.getLogger(PaqueteController.class);
    @Autowired
    PaquetesVisualcashService pvcs;
    
    @GetMapping("/visualcash")
    public ResponseEntity<Paquete> list() throws IOException{
        CoinPayments api = CoinPayments.builder()
        .publicKey("f5fa63a03680161eaefb6115f03709d93d03ce61e51afe8882633c2fef5aebd7")
        .privateKey("ee97e95FCE9Db29119a44A54679429198720b6f2B8a61bdBF3A72D522aC32e37")
        .client(HttpClients.createDefault()).build();

ResponseWrapper<BasicInfoResponse> accountInfo = api.sendRequest(new CoinPaymentsBasicAccountInfoRequest());
logger.error("Account: " + accountInfo.getResult());
return new ResponseEntity(accountInfo.getResult(), HttpStatus.OK);
        //return new ResponseEntity(pvcs.list(), HttpStatus.OK);
    }
}
