/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Fabian
 */
@Embeddable
public class UsuarioPaqueteId implements Serializable {
    @Column(name="id_user")
    private int idUser; 
    @Column(name="id_paquete")
    private int idPaquete; 

    public UsuarioPaqueteId(int idUser, int idPaquete) {
        this.idUser = idUser;
        this.idPaquete = idPaquete;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }
    
}
