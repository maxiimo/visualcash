/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.entity;

import com.backend.visualcash.security.entity.Usuario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name="user_paquete")
public class UsuarioPaquete {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="id_paquete", nullable=false)
    private Paquete paquete;

    @NotNull    
    @OneToOne
    @JoinColumn(name="id_user", nullable=false)
    private Usuario usuario;
    
    @Column(columnDefinition = "tinyint",  length = 2)
    private int dias;
    
    @Column(columnDefinition = "tinyint",  length = 2)
    private int n_anuncios;
    
    @Column(columnDefinition = "tinyint", length = 2)
    private int dias_completados;

    public UsuarioPaquete() {
    }

    public UsuarioPaquete(Usuario usuario, Paquete paquete, int dias, int n_anuncios, int dias_completados) {
        this.usuario = usuario;
        this.paquete = paquete;
        this.dias = dias;
        this.n_anuncios = n_anuncios;
        this.dias_completados = dias_completados;
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

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getN_anuncios() {
        return n_anuncios;
    }

    public void setN_anuncios(int n_anuncios) {
        this.n_anuncios = n_anuncios;
    }

    public int getDias_completados() {
        return dias_completados;
    }

    public void setDias_completados(int dias_completados) {
        this.dias_completados = dias_completados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
