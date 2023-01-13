package com.talissonmelo.modelo.exceptions;

import com.talissonmelo.modelo.dto.UsuarioDto;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RespostaValidacao {
    private String mensagem;
    private Collection<CampoValidado> validados;

    public RespostaValidacao(String mensagem, Collection<CampoValidado> validados) {
        this.mensagem = mensagem;
        this.validados = validados;
    }

    public static <T> RespostaValidacao criarRespostaValidacao(Set<ConstraintViolation<T>> validacoes){
        List<CampoValidado> erros = validacoes
                .stream()
                .map((validacao) -> new CampoValidado(validacao.getPropertyPath().toString(), validacao.getMessage()))
                .collect(Collectors.toList());

        String mensagem = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";
        return new RespostaValidacao(mensagem, erros);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Collection<CampoValidado> getValidados() {
        return validados;
    }

    public void setValidados(Collection<CampoValidado> validados) {
        this.validados = validados;
    }
}
