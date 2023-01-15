package com.backend.visualcash.dto;

public class Mensaje {
    private String message;

    public Mensaje(String message) {
        this.message = message;
    }
    public Mensaje() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
