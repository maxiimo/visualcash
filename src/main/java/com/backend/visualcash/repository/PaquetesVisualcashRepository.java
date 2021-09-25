package com.backend.visualcash.repository;

import com.backend.visualcash.entity.Paquete;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaquetesVisualcashRepository extends JpaRepository<Paquete, Integer> {
    Optional<Paquete> findByNombre(String nombre);
    @Override
    public List<Paquete> findAll();
    boolean existsByNombre(String nombre);
}
