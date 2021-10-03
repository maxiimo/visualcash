/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.service;

import com.backend.visualcash.entity.Paquete;
import com.backend.visualcash.entity.Payments;
import com.backend.visualcash.repository.PaymentRepository;
import com.backend.visualcash.security.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fabian
 */
    @Service
@Transactional
public class PaymenService {

    @Autowired
    PaymentRepository paymentRepository;

    public Optional<Paquete> getOne(Usuario usuario){
        return paymentRepository.findByUsuario(usuario);
    }

    public void  save(Payments payment){
        paymentRepository.save(payment);
    }

    public void delete(int id){
        paymentRepository.deleteById(id);
    }
}
