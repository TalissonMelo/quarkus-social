package com.talissonmelo.modelo.dto;

import java.util.List;

public class SeguidorPorUsuarioResponse {
    private Integer quantidadeSeguidores;
    private List<SeguidorResposta> seguidorRespostas;

    public SeguidorPorUsuarioResponse() {
    }

    public SeguidorPorUsuarioResponse(Integer quantidadeSeguidores, List<SeguidorResposta> seguidorRespostas) {
        this.quantidadeSeguidores = quantidadeSeguidores;
        this.seguidorRespostas = seguidorRespostas;
    }

    public Integer getQuantidadeSeguidores() {
        return quantidadeSeguidores;
    }

    public void setQuantidadeSeguidores(Integer quantidadeSeguidores) {
        this.quantidadeSeguidores = quantidadeSeguidores;
    }

    public List<SeguidorResposta> getSeguidorRespostas() {
        return seguidorRespostas;
    }

    public void setSeguidorRespostas(List<SeguidorResposta> seguidorRespostas) {
        this.seguidorRespostas = seguidorRespostas;
    }
}
