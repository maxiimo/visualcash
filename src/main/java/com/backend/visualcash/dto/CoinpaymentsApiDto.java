/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.dto;

import java.io.IOException;
import org.apache.http.impl.client.HttpClients;
import org.brunocvcunha.coinpayments.CoinPayments;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.brunocvcunha.coinpayments.model.ResponseWrapper;
import org.brunocvcunha.coinpayments.requests.CoinPaymentsCreateTransactionRequest;

/**
 *
 * @author Fabian
 */
public class CoinpaymentsApiDto {

    CoinPayments api;
    
    public CoinpaymentsApiDto() {
        this.api = CoinPayments.builder()
        .publicKey("f5fa63a03680161eaefb6115f03709d93d03ce61e51afe8882633c2fef5aebd7")
        .privateKey("ee97e95FCE9Db29119a44A54679429198720b6f2B8a61bdBF3A72D522aC32e37")
        .client(HttpClients.createDefault()).build();
    }
    
    public ResponseWrapper<CreateTransactionResponse> createTransaction(String to_currency, String email, Double amount) throws IOException{
        
        return this.api.sendRequest(CoinPaymentsCreateTransactionRequest.builder().amount(10)
        .amount(amount)
        .currencyPrice("USD")
        .currencyTransfer(to_currency)
        .callbackUrl("<callback-url-if-wanted>")
        .buyerEmail(email)
        .build());
    }
}
