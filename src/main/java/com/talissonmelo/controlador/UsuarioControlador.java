package com.talissonmelo.controlador;

import com.talissonmelo.modelo.Usuario;
import com.talissonmelo.modelo.dto.UsuarioDto;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
