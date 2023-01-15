/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.dto;

/**
 *
 * @author Fabian
 */
public class ResponseMensaje {
    private String message;

    public ResponseMensaje(String message) {
        this.message = message;
    }
    public ResponseMensaje() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
