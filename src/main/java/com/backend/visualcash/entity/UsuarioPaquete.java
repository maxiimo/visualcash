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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fabian
 */
@Entity
public class UsuarioPaquete  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_paquete")
    private Paquete paquete;
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    @NotNull
    @Column(length = 2, columnDefinition = "tinyint(2) default 50")
    private int dias;
   
    @NotNull
    @Column(length = 2, columnDefinition = "tinyint(2)")
    private int nAnuncios;
    
    @NotNull
    @Column(length = 2, columnDefinition = "tinyint(2) default 0")
    private int diasCompletados;
 
    @NotNull
    @Column(length = 12, columnDefinition = "decimal(10,2) default 0")
    private double totalAcumulado;
   
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompletedAllAnuncios = null;
    
    public UsuarioPaquete() {
    }

    public UsuarioPaquete(@NotNull Paquete paquete, @NotNull Usuario usuario, @NotNull int nAnuncios) {
        this.paquete = paquete;
        this.usuario = usuario;
        this.nAnuncios = nAnuncios;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getnAnuncios() {
        return nAnuncios;
    }

    public void setnAnuncios(int nAnuncios) {
        this.nAnuncios = nAnuncios;
    }

    public int getDiasCompletados() {
        return diasCompletados;
    }

    public void setDiasCompletados(int diasCompletados) {
        this.diasCompletados = diasCompletados;
    }

    public double getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(double totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }

    public Date getFechaCompletedAllAnuncios() {
        return fechaCompletedAllAnuncios;
    }

    public void setFechaCompletedAllAnuncios(Date fechaCompletedAllAnuncios) {
        this.fechaCompletedAllAnuncios = fechaCompletedAllAnuncios;
    }

    
}
