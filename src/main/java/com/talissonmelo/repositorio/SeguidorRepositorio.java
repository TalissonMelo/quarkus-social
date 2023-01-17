package com.talissonmelo.repositorio;

import com.talissonmelo.modelo.Seguidor;
import com.talissonmelo.modelo.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class SeguidorRepositorio implements PanacheRepository<Seguidor> {
    public Boolean ehValidoSeguirUsuario(Usuario usuario, Usuario seguidor) {
        //Map<String, Object> params = new HashMap<>();
        //params.put("usuario", usuario);
        //params.put("seguidor", seguidor);
        Map<String, Object> params = Parameters.with("seguidor", seguidor).and("usuario", usuario).map();
        PanacheQuery<Seguidor> query = find("seguidor = :seguidor and usuario = :usuario", params);
        Optional<Seguidor> optional = query.firstResultOptional();
        return optional.isPresent();
    }

    public List<Seguidor> buscarPorIdUsuario(Long idUsuario) {
        PanacheQuery<Seguidor> query = find("usuario.id", idUsuario);
        return query.list();
    }
}
