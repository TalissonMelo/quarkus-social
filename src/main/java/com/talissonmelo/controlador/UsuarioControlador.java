package com.talissonmelo.controlador;

import com.talissonmelo.modelo.Usuario;
import com.talissonmelo.modelo.dto.UsuarioDto;
import com.talissonmelo.repositorio.UsuarioRepositorio;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioControlador {

    private UsuarioRepositorio repositorio;

    @Inject
    public UsuarioControlador(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @POST
    @Transactional
    public Response criarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.criarUsuario(usuarioDto.getNome(), usuarioDto.getIdade());
        repositorio.persist(usuario);
        return Response.ok(usuario).build();
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
        if(usuario != null) {
            repositorio.delete(usuario);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, UsuarioDto usuarioDto) {
        Usuario usuario = repositorio.findById(id);
        if(usuario != null) {
            usuario.setIdade(usuarioDto.getIdade());
            usuario.setNome(usuarioDto.getNome());
            repositorio.persist(usuario);
            return Response.ok(usuario).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
