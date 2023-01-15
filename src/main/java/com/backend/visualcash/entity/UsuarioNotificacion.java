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
public class UsuarioNotificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario", nullable=false)
    private Usuario usuario;
    
    @NotNull
    @Column(nullable = false)
    private String titulo;
    
    @NotNull 
    @Column(nullable = false, columnDefinition = "text")
    private String mensaje;

    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewdAt = null;

    public UsuarioNotificacion(Usuario usuario, String titulo, String mensaje) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }
    
    public UsuarioNotificacion() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getViewdAt() {
        return viewdAt;
    }

    public void setViewdAt(Date viewdAt) {
        this.viewdAt = viewdAt;
    }

   

    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }   
    
}
