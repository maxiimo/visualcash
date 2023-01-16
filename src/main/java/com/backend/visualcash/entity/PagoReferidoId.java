package com.backend.visualcash.entity;

import com.backend.visualcash.security.entity.Usuario;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class PagoReferidoId implements Serializable {
    private Usuario usuario;
    private Usuario usuarioReferido;
    private int nivel;

    public PagoReferidoId() {
    }

    public PagoReferidoId(Usuario usuario, Usuario usuarioReferido, int nivel) {
        this.usuario = usuario;
        this.usuarioReferido = usuarioReferido;
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagoReferidoId that = (PagoReferidoId) o;
        return nivel == that.nivel && usuario.equals(that.usuario) && usuarioReferido.equals(that.usuarioReferido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, usuarioReferido, nivel);
    }
}
