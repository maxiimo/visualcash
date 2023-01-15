/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.repository;

import com.backend.visualcash.dto.ResponsePayment;
import com.backend.visualcash.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabian
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer>{
    @Query(value = "validePaymentPaqueteVC(:txn_id, :status);", nativeQuery = true)
    public ResponsePayment validePaymentPaqueteVC(String txn_id, String status);    
}
