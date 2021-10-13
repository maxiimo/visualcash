
import com.backend.visualcash.security.entity.Usuario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabian
 */
@Entity
public class PagoReferidoSegundoNivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_referido")
    private Usuario usuarioReferido;

    @NotNull
    @Column(columnDefinition = "decimal(10,2)")
    private Double pago;
    public PagoReferidoSegundoNivel() {
    }

    public PagoReferidoSegundoNivel(Integer id, Usuario usuario, Usuario usuarioReferido) {
        this.id = id;
        this.usuario = usuario;
        this.usuarioReferido = usuarioReferido;
        this.pago = pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioReferido() {
        return usuarioReferido;
    }

    public void setUsuarioReferido(Usuario usuarioReferido) {
        this.usuarioReferido = usuarioReferido;
    }

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }
    
    
}