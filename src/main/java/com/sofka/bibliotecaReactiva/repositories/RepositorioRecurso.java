package com.sofka.bibliotecaReactiva.repositories;

import com.sofka.bibliotecaReactiva.collections.Recurso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RepositorioRecurso extends ReactiveCrudRepository<Recurso, String> {
    Flux<Recurso> findAllBytipoRecurso(final String tipoRecurso);
    Flux<Recurso> findAllBytematicaRecurso(final String tematicaRecurso)
    Flux<Recurso> findAllBytipoYTematica(final String tipoRecurso, final String tematicaRecurso);
}
