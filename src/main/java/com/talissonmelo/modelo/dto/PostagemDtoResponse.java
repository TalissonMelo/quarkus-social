package com.talissonmelo.modelo.dto;

import com.talissonmelo.modelo.Postagem;

import java.time.LocalDateTime;

public class PostagemDtoResponse {
    private String nome;
    private LocalDateTime data;

    public PostagemDtoResponse(String nome, LocalDateTime data) {
        this.nome = nome;
        this.data = data;
    }

    public static PostagemDtoResponse setPostagem(Postagem postagem) {
        PostagemDtoResponse postagemDtoResponse = new PostagemDtoResponse(postagem.getNome(), postagem.getData());
        return postagemDtoResponse;
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
}
