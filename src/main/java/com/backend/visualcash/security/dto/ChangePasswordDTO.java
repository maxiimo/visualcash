package com.backend.visualcash.security.dto;
import javax.validation.constraints.NotBlank;

public class ChangePasswordDTO {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String token;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}