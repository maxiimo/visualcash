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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Fabian
 */
@Entity
public class UsuarioPaquete  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="id_paquete")
    private Paquete paquete;
    
    @OneToOne
    @JoinColumn(name="id_user")
    private Usuario usuario;
    
    @Column(columnDefinition = "tinyint",  length = 2)
    private int dias;
    
    @Column(columnDefinition = "tinyint",  length = 2)
    private int n_anuncios;
    
    @Column(columnDefinition = "tinyint", length = 2)
    private int dias_completados;

    public UsuarioPaquete() {
    }

    public UsuarioPaquete(Paquete paquete, Usuario usuario, int dias, int n_anuncios, int dias_completados) {
        this.paquete = paquete;
        this.usuario = usuario;
        this.dias = dias;
        this.n_anuncios = n_anuncios;
        this.dias_completados = dias_completados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    
    
}
