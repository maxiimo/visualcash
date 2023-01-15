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
public interface UserInfo {
    float getSaldo();
    float getCoins();
    String getPaquete();
    int getComunidad();
    int getDiasPaq();
    int getDiasPaqComplete();
    int getNAnuncios();
    String getLastRefers(); 
}