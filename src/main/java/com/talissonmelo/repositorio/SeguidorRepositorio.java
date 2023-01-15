package com.talissonmelo.repositorio;

import com.talissonmelo.modelo.Seguidor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SeguidorRepositorio implements PanacheRepository<Seguidor> {
}
