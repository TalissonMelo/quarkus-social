package com.talissonmelo.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Postagem() {
    }

    public Postagem(Long id, String nome, LocalDateTime data, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
