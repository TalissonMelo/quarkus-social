package com.talissonmelo.controlador;

import com.talissonmelo.modelo.Postagem;
import com.talissonmelo.modelo.Usuario;
import com.talissonmelo.modelo.dto.PostagemDto;
import com.talissonmelo.repositorio.PostagemRepositorio;
import com.talissonmelo.repositorio.UsuarioRepositorio;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/usuarios/{idUsuario}/postagens")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostagemControlador {

    private UsuarioRepositorio usuarioRepositorio;
    private PostagemRepositorio repositorio;

    @Inject
    public PostagemControlador(UsuarioRepositorio usuarioRepositorio, PostagemRepositorio repositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.repositorio = repositorio;
    }

    @POST
    @Transactional
    public Response salvar(@PathParam("idUsuario") Long idUsuario, PostagemDto postagemDto) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Postagem postagem = this.criarPostagem(postagemDto, usuario);
        this.repositorio.persist(postagem);
        return Response.status(Response.Status.CREATED).entity(postagem).build();
    }

    @GET
    public Response listar(@PathParam("idUsuario") Long idUsuario) {
        Usuario usuario = usuarioRepositorio.findById(idUsuario);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PanacheQuery<Postagem> postagens = repositorio.find( "usuario", usuario);
        return Response.status(Response.Status.OK).entity(postagens.list()).build();
    }

    private Postagem criarPostagem(PostagemDto postagemDto, Usuario usuario) {
        Postagem postagem = new Postagem();
        postagem.setNome(postagemDto.getDescricao());
        postagem.setUsuario(usuario);
        postagem.setData(LocalDateTime.now());
        return postagem;
    }
}
