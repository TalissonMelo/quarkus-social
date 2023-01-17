package com.talissonmelo.modelo.dto;

import com.talissonmelo.modelo.Seguidor;

public class SeguidorResposta {
    private Long id;
    private String nome;

    public SeguidorResposta() {
    }

    public SeguidorResposta(Seguidor seguidor) {
        this(seguidor.getSeguidor().getId(), seguidor.getSeguidor().getNome());
    }

    public SeguidorResposta(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
