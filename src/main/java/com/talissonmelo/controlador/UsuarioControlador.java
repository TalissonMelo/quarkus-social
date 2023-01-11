package com.talissonmelo.controlador;

import com.talissonmelo.modelo.Usuario;
import com.talissonmelo.modelo.dto.UsuarioDto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioControlador {

    @POST
    @Transactional
    public Response criarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.criarUsuario(usuarioDto.getNome(), usuarioDto.getIdade());
        usuario.persist();
        return Response.ok(usuario).build();
    }

    @GET
    public Response listar() {
        PanacheQuery<Usuario> usuarios = Usuario.findAll();
        return Response.ok(usuarios.list()).build();
    }
}
