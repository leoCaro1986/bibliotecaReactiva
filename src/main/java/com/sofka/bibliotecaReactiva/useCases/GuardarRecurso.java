package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface GuardarRecurso {
    Mono<RecursoDTO> apply(@Valid RecursoDTO recursoDTO);
}
