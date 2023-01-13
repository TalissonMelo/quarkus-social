package com.talissonmelo.modelo.dto;

import javax.validation.constraints.NotBlank;

public class PostagemDto {

    @NotBlank(message ="Descricao e obrigatório!.")
    private String descricao;

    public PostagemDto() {
    }

    public PostagemDto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
