/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.dto;

/**
 *
 * @author Fabian
 */
public class PaqueteVisualcashDTO {
    
    private Integer id;    
    
    private String nombre;
    
    private String imagen;
    
    private String descripcion;  
    
    private int precio;
    
    private int coins;
    
    private Double pago_diario;
    
    private int n_anuncios;

    public PaqueteVisualcashDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Double getPago_diario() {
        return pago_diario;
    }

    public void setPago_diario(Double pago_diario) {
        this.pago_diario = pago_diario;
    }

    public int getN_anuncios() {
        return n_anuncios;
    }

    public void setN_anuncios(int n_anuncios) {
        this.n_anuncios = n_anuncios;
    }
    
}
