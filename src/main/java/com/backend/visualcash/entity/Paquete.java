package com.backend.visualcash.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Column(nullable = false, length = 40)
    private String nombre;
    
    @NotNull
    @Column(nullable = false, length = 1000)
    private String imagen;
    
    @NotNull
    @Column(nullable = false, length = 255)
    private String descripcion;    
    
    @NotNull
    @Column(nullable = false)
    private float precio;
    
    @NotNull
    @Column(nullable = false)
    private float coins;
    
    @NotNull
    @Column(nullable = false)
    private float pago_diario;
    
    @NotNull
    @Column(nullable = false)
    private float n_anuncios;
    
    @NotNull
    @Column(nullable = false)
    private boolean active;
    
    @OneToMany(mappedBy = "paquete")
    private Set<UsuarioPaquete> usuario;

    public Paquete() {
    }

    public Paquete(int id, String nombre, String imagen, String descripcion, float precio, float coins, float pago_diario, float n_anuncios) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.coins = coins;
        this.pago_diario = pago_diario;
        this.n_anuncios = n_anuncios;
    }
    
    public Paquete(int id, String nombre, String imagen, String descripcion, float precio, float coins, float pago_diario, float n_anuncios, boolean active) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.coins = coins;
        this.pago_diario = pago_diario;
        this.n_anuncios = n_anuncios;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCoins() {
        return coins;
    }

    public void setCoins(float coins) {
        this.coins = coins;
    }

    public float getPago_diario() {
        return pago_diario;
    }

    public void setPago_diario(float pago_diario) {
        this.pago_diario = pago_diario;
    }

    public float getN_anuncios() {
        return n_anuncios;
    }

    public void setN_anuncios(float n_anuncios) {
        this.n_anuncios = n_anuncios;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
