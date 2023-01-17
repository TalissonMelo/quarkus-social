package com.talissonmelo.controlador;

import com.talissonmelo.modelo.Seguidor;
import com.talissonmelo.modelo.Usuario;
import com.talissonmelo.modelo.dto.SeguidorDto;
import com.talissonmelo.modelo.dto.SeguidorPorUsuarioResponse;
import com.talissonmelo.modelo.dto.SeguidorResposta;
import com.talissonmelo.modelo.exceptions.RespostaValidacao;
import com.talissonmelo.repositorio.SeguidorRepositorio;
import com.talissonmelo.repositorio.UsuarioRepositorio;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/usuarios/{idUsuario}/seguidores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguidorControlador {
    private SeguidorRepositorio repositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private Validator validator;

    @Inject
    public SeguidorControlador(SeguidorRepositorio repositorio, UsuarioRepositorio usuarioRepositorio, Validator validator) {
        this.repositorio = repositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.validator = validator;
    }

    @PUT
    @Transactional
    public Response seguirUsuario(@PathParam("idUsuario") Long idUsuario, SeguidorDto seguidorDto) {
        Set<ConstraintViolation<SeguidorDto>> validacao = this.validator.validate(seguidorDto);
        if (!validacao.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(RespostaValidacao.criarRespostaValidacao(validacao)).build();
        }
        Usuario usuario = usuarioRepositorio.findById(idUsuario);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (idUsuario.equals(seguidorDto.getIdSeguidor())) {
            return Response.status(Response.Status.CONFLICT).entity("Usuário não pode seguir a si mesmo.").build();
        }

        Usuario usuarioSeguidor = usuarioRepositorio.findById(seguidorDto.getIdSeguidor());

        Boolean seguirUsuario = repositorio.ehValidoSeguirUsuario(usuario, usuarioSeguidor);

        if (!seguirUsuario) {
            Seguidor seguidor = new Seguidor();
            seguidor.setUsuario(usuario);
            seguidor.setSeguidor(usuarioSeguidor);
            repositorio.persist(seguidor);
            return Response.status(Response.Status.CREATED).entity(seguidor).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response listarSeguidoresPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Seguidor> seguidores = repositorio.buscarPorIdUsuario(idUsuario);
        if(seguidores.size() > 0) {
            SeguidorPorUsuarioResponse seguidorPorUsuarioResponse = new SeguidorPorUsuarioResponse();
            seguidorPorUsuarioResponse.setQuantidadeSeguidores(seguidores.size());
            var list = seguidores.stream().map(SeguidorResposta::new).collect(Collectors.toList());
            seguidorPorUsuarioResponse.setSeguidorRespostas(list);
            return Response.ok(seguidorPorUsuarioResponse).build();
        }
        return Response.ok(seguidores).build();
    }
}
