/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.repository;

import com.backend.visualcash.entity.Payments;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabian
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer>{

    public Optional<Payments> findByTxnIdAndStatus(String txn_id, String status);
    
}
