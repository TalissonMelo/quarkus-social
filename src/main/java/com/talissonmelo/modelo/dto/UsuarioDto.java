package com.talissonmelo.modelo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioDto {

    @NotBlank(message ="Nome e obrigatório!.")
    private String nome;
    @NotNull(message = "Idade e obrigatório!.")
    private Integer idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
