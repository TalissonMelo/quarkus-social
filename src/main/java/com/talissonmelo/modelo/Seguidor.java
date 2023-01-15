package com.talissonmelo.modelo;

import javax.persistence.*;

@Entity
public class Seguidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_seguidor")
    private Usuario seguidor;

    public Seguidor() {
    }

    public Seguidor(Long id, Usuario usuario, Usuario seguidor) {
        this.id = id;
        this.usuario = usuario;
        this.seguidor = seguidor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(Usuario seguidor) {
        this.seguidor = seguidor;
    }
}
