package com.sofka.bibliotecaReactiva.repositories;

import com.sofka.bibliotecaReactiva.collections.Recurso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RepositorioRecurso extends ReactiveCrudRepository<Recurso, String> {
    Flux<Recurso> findAllByTipo(final String tipo);
    Flux<Recurso> findAllByTematica(final String tematica);
    Flux<Recurso> findAllByTipoAndTematica(final String tipo, final String tematica);
}
