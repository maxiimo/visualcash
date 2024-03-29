/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.entity;

import com.backend.visualcash.security.entity.Usuario;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private String from_currency = "USD";
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String to_currency;
    
    @NotNull
    @Column(nullable = false, columnDefinition = "numeric(10,2)")
    private Double entered_amount;  
    
    @NotNull
    @Column(nullable = false)
    private Double amount; 
    
    @NotNull
    @Column(name = "gateway_id", nullable = false, length = 255)
    private String gatewayId;   
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String gateway_url;    
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String status;        
    
    
    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = null; 
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", 
            columnDefinition="TIMESTAMP")
    private Date createdAt = new Date();
    
    @NotNull    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario", nullable=false)
    private Usuario usuario;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_paquete", nullable=false)
    private Paquete paquete;

    public Payments() {        
    }    

    public Payments(@NotNull String to_currency, @NotNull Double entered_amount,
            @NotNull Double amount, @NotNull String gatewayId, @NotNull String gateway_url, @NotNull String status,
            @NotNull Usuario usuario, @NotNull Paquete paquete) {
        this.to_currency = to_currency;
        this.entered_amount = entered_amount;
        this.amount = amount;
        this.gatewayId = gatewayId;
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

    public Double getEntered_amount() {
        return entered_amount;
    }

    public void setEntered_amount(Double entered_amount) {
        this.entered_amount = entered_amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getGateway_id() {
        return gatewayId;
    }

    public void setGateway_id(String gatewayId) {
        this.gatewayId = gatewayId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
