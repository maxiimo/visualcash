package com.backend.visualcash.security.entity;

import com.backend.visualcash.entity.Payments;
import com.backend.visualcash.entity.UsuarioPaquete;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true, length = 200)
    private String email;
    
    @Column(nullable = false, length = 255)
    private String password;
    
    @Column(length = 64)
    private String verificationCode;
    
    @Column(nullable = false)
    private boolean active = true;
    
    @Column(nullable = false, columnDefinition = "decimal(10,2) default 00.00")
    private double saldo;

    @Column(nullable = false, columnDefinition = "decimal(10,2) default 00.00")
    private double coins;
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "id_user"),
    inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UsuarioPaquete usuarioPaquete;

    @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payments> payment;
    
    @NotNull
    @Column(nullable = false, length = 14)
    private int my_code_refer;
    
    @NotNull
    @Column(nullable = false, length = 14)
    private int refer_code_refer;
    
    public Usuario() {
    }
    
    public Usuario(@NotNull String email, @NotNull String password, @NotNull String verificationCode) {
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
    }
    
    public Usuario(@NotNull String email, @NotNull String password, @NotNull String verificationCode,
            @NotNull boolean active, @NotNull int my_code_refer) {
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
        this.active = active;
        this.my_code_refer = my_code_refer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public UsuarioPaquete getPaquete() {
        return usuarioPaquete;
    }

    public void setPaquete(UsuarioPaquete usuarioPaquete) {
        this.usuarioPaquete = usuarioPaquete;
    }

    public Set<Payments> getPayment() {
        return payment;
    }

    public void setPayment(Set<Payments> payment) {
        this.payment = payment;
    }

    public int getMy_code_refer() {
        return my_code_refer;
    }

    public void setMy_code_refer(int my_code_refer) {
        this.my_code_refer = my_code_refer;
    }

    public int getRefer_code_refer() {
        return refer_code_refer;
    }

    public void setRefer_code_refer(int refer_code_refer) {
        this.refer_code_refer = refer_code_refer;
    }    
}
