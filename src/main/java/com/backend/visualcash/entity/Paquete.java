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
    private Double precio;
    
    @NotNull
    @Column(nullable = false)
    private int coins;
    
    @NotNull
    @Column(nullable = false)
    private Double pago_diario;
    
    @NotNull
    @Column(nullable = false)
    private int n_anuncios;
    
    @Column(nullable = false)
    private boolean active = true;
    
    @OneToMany(mappedBy = "paquete")
    private Set<UsuarioPaquete> usuarioPaquete;
    
    @OneToMany(mappedBy="paquete")
    private Set<Payments> payment;

    public Paquete() {
    }

    public Paquete(String nombre, String imagen, String descripcion, Double precio, int coins, Double pago_diario, int n_anuncios) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.coins = coins;
        this.pago_diario = pago_diario;
        this.n_anuncios = n_anuncios;
    }
    
    public Paquete(String nombre, String imagen, String descripcion, Double precio, int coins, Double pago_diario, int n_anuncios, boolean active) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<UsuarioPaquete> getUsuario() {
        return usuarioPaquete;
    }

    public void setUsuario(Set<UsuarioPaquete> usuarioPaquete) {
        this.usuarioPaquete = usuarioPaquete;
    }

    public Set<Payments> getPayment() {
        return payment;
    }

    public void setPayment(Set<Payments> payment) {
        this.payment = payment;
    }
    

}
