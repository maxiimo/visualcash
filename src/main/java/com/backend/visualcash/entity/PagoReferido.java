/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.entity;

import com.backend.visualcash.security.entity.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fabian
 */
@Entity
@IdClass(PagoReferidoId.class)
public class PagoReferido {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;
    
    @NotNull
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="id_usuario")
    @Id
    private Usuario usuario;
    
    @NotNull
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="id_referido")
    @Id
    private Usuario usuarioReferido;

    @NotNull
    @Column(columnDefinition = "numeric(10,2)")
    private Double pago;

    @NotNull
    @Column(columnDefinition = "smallint")
    private int nivel;

    public PagoReferido() {
    }

    public PagoReferido(Usuario usuario, Usuario usuarioReferido, Double pago, int nivel) {
        this.usuario = usuario;
        this.usuarioReferido = usuarioReferido;
        this.pago = pago;
        this.nivel = nivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioReferido() {
        return usuarioReferido;
    }

    public void setUsuarioReferido(Usuario usuarioReferido) {
        this.usuarioReferido = usuarioReferido;
    }

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }
    
    
}
