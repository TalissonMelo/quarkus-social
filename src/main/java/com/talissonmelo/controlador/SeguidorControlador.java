package com.talissonmelo.controlador;

import com.talissonmelo.repositorio.SeguidorRepositorio;
import com.talissonmelo.repositorio.UsuarioRepositorio;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/usuarios/{idUsuario}/seguidores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguidorControlador {

    private SeguidorRepositorio repositorio;
    private UsuarioRepositorio usuarioRepositorio;

    @Inject
    public SeguidorControlador(SeguidorRepositorio repositorio, UsuarioRepositorio usuarioRepositorio) {
        this.repositorio = repositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }
}
