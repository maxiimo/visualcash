/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.entity;

import com.backend.visualcash.security.entity.Usuario;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fabian
 */
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String producto;
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String from_currency;
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String to_currency;
    
    @NotNull
    @Column(nullable = false)
    private int entered_amount;  
    
    @NotNull
    @Column(nullable = false)
    private int amount; 
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String gateway_id;   
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String gateway_url;    
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String status;    
    
    
    @NotNull    
    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private Usuario usuario;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_paquete", nullable=false)
    private Paquete paquete;

    public Payments() {        
    }    

    public Payments(@NotNull String producto, @NotNull String from_currency, @NotNull String to_currency, @NotNull int entered_amount,
            @NotNull int amount, @NotNull String gateway_id, @NotNull String gateway_url, @NotNull String status,
            Usuario usuario, Paquete paquete) {
        this.producto = producto;
        this.from_currency = from_currency;
        this.to_currency = to_currency;
        this.entered_amount = entered_amount;
        this.amount = amount;
        this.gateway_id = gateway_id;
        this.gateway_url = gateway_url;
        this.status = status;
        this.usuario = usuario;
        this.paquete = paquete;  
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFrom_currency() {
        return from_currency;
    }

    public void setFrom_currency(String from_currency) {
        this.from_currency = from_currency;
    }

    public String getTo_currency() {
        return to_currency;
    }

    public void setTo_currency(String to_currency) {
        this.to_currency = to_currency;
    }

    public int getEntered_amount() {
        return entered_amount;
    }

    public void setEntered_amount(int entered_amount) {
        this.entered_amount = entered_amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getGateway_id() {
        return gateway_id;
    }

    public void setGateway_id(String gateway_id) {
        this.gateway_id = gateway_id;
    }

    public String getGateway_url() {
        return gateway_url;
    }

    public void setGateway_url(String gateway_url) {
        this.gateway_url = gateway_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    
    
}
