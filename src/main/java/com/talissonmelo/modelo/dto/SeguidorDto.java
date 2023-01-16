package com.talissonmelo.modelo.dto;

import javax.validation.constraints.NotNull;

public class SeguidorDto {

    @NotNull(message = "Preencha o campo obrigatório!")
    private long idSeguidor;

    public SeguidorDto() {
    }

    public SeguidorDto(long idSeguidor) {
        this.idSeguidor = idSeguidor;
    }

    public long getIdSeguidor() {
        return idSeguidor;
    }

    public void setIdSeguidor(long idSeguidor) {
        this.idSeguidor = idSeguidor;
    }
}
