package com.talissonmelo.repositorio;

import com.talissonmelo.modelo.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepositorio implements PanacheRepository<Usuario> {
}
