package com.talissonmelo.controlador;

import com.talissonmelo.modelo.dto.UsuarioDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioControlador {

    @POST
    public Response criarUsuario(UsuarioDto usuarioDto) {
        return Response.ok().build();
    }
}
