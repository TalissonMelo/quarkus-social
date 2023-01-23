package com.talissonmelo.controlador;

import com.talissonmelo.modelo.Usuario;
import com.talissonmelo.modelo.dto.UsuarioDto;
import com.talissonmelo.modelo.exceptions.RespostaValidacao;
import com.talissonmelo.repositorio.UsuarioRepositorio;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioControlador {

    private UsuarioRepositorio repositorio;
    private Validator validator;

    @Inject
    public UsuarioControlador(UsuarioRepositorio repositorio, Validator validator) {
        this.repositorio = repositorio;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response criarUsuario(UsuarioDto usuarioDto) {

        Set<ConstraintViolation<UsuarioDto>> validacao = this.validator.validate(usuarioDto);
        if (!validacao.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(RespostaValidacao.criarRespostaValidacao(validacao)).build();
        }
        Usuario usuario = new Usuario();
        usuario.criarUsuario(usuarioDto.getNome(), usuarioDto.getIdade());
        repositorio.persist(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @GET
    public Response listar() {
        PanacheQuery<Usuario> usuarios = repositorio.findAll();
        return Response.ok(usuarios.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        Usuario usuario = repositorio.findById(id);
        if (usuario != null) {
            repositorio.delete(usuario);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, UsuarioDto usuarioDto) {
        Usuario usuario = repositorio.findById(id);
        if (usuario != null) {
            usuario.setIdade(usuarioDto.getIdade());
            usuario.setNome(usuarioDto.getNome());
            repositorio.persist(usuario);
            return Response.ok(usuario).build();
        }
        return Response.noContent().build();
    }
}
