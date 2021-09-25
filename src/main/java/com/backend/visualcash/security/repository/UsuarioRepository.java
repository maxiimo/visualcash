package com.backend.visualcash.security.repository;

import com.backend.visualcash.security.dto.UserInfo;
import com.backend.visualcash.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM Usuario u WHERE u.verificationCode = ?1")
    public Usuario findByVerificationCode(String code);
    public Usuario findByVerificationCodeAndEmail(String code, String email);
    @Query(value = "CALL get_info_user(:email);", nativeQuery = true)
    public Optional<UserInfo> getUserInfo(String email);
}
