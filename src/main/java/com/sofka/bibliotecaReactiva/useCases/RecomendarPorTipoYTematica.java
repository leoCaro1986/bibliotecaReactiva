package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

public interface RecomendarPorTipoYTematica {
    Flux<RecursoDTO> get(@Valid String tipoRecurso, @Valid String tematicaRecurso);
}
