package com.talissonmelo.repositorio;

import com.talissonmelo.modelo.Postagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostagemRepositorio implements PanacheRepository<Postagem> {
}
