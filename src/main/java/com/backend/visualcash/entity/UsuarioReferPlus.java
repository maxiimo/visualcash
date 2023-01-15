/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.entity;

import com.backend.visualcash.security.entity.Usuario;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fabian
 */
@Entity
public class UsuarioReferPlus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario", nullable=false)
    private Usuario usuario;
    
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario_referido", nullable=false)
    private Usuario usuarioreferido;
    
    @NotNull
    private Double plus;
    
    @NotNull
    private int nivel;

    public UsuarioReferPlus() {
    }

    public UsuarioReferPlus(Integer id, Usuario usuario, Usuario usuarioreferido, Double plus, int nivel) {
        this.id = id;
        this.usuario = usuario;
        this.usuarioreferido = usuarioreferido;
        this.plus = plus;
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

    public Usuario getUsuarioreferido() {
        return usuarioreferido;
    }

    public void setUsuarioreferido(Usuario usuarioreferido) {
        this.usuarioreferido = usuarioreferido;
    }

    public Double getPlus() {
        return plus;
    }

    public void setPlus(Double plus) {
        this.plus = plus;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }    
    
}
