package com.talissonmelo.controlador;

import com.talissonmelo.modelo.Postagem;
import com.talissonmelo.modelo.Usuario;
import com.talissonmelo.modelo.dto.PostagemDto;
import com.talissonmelo.modelo.dto.PostagemDtoResponse;
import com.talissonmelo.repositorio.PostagemRepositorio;
import com.talissonmelo.repositorio.UsuarioRepositorio;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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
        PanacheQuery<Postagem> query = repositorio.find("usuario", Sort.by("data", Sort.Direction.Descending), usuario);
        return Response.status(Response.Status.OK).entity(query.list().stream().map(PostagemDtoResponse::setPostagem).collect(Collectors.toList())).build();
    }

    private Postagem criarPostagem(PostagemDto postagemDto, Usuario usuario) {
        Postagem postagem = new Postagem();
        postagem.setNome(postagemDto.getDescricao());
        postagem.setUsuario(usuario);
        postagem.setData(LocalDateTime.now());
        return postagem;
    }
}
