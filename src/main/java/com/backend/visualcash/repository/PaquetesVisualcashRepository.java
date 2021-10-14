package com.backend.visualcash.repository;

import com.backend.visualcash.entity.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaquetesVisualcashRepository extends JpaRepository<Paquete, Integer> {
    Optional<Paquete> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
