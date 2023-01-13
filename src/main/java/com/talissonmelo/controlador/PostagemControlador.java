package com.talissonmelo.controlador;

import com.talissonmelo.modelo.Usuario;
import com.talissonmelo.repositorio.UsuarioRepositorio;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios/{idUsuario}/postagens")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostagemControlador {

    private UsuarioRepositorio repositorio;

    @Inject
    public PostagemControlador(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @POST
    @Transactional
    public Response salvar(@PathParam("idUsuario") Long idUsuario) {
        Usuario usuario = repositorio.findById(idUsuario);
        if(usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).entity(null).build();
    }

    @GET
    public Response listar() {
        return Response.ok().build();
    }
}
