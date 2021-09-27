package com.backend.visualcash.security.repository;

import com.backend.visualcash.security.entity.Rol;
import com.backend.visualcash.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    boolean existByRolNombre(RolNombre rolNombre);
}
