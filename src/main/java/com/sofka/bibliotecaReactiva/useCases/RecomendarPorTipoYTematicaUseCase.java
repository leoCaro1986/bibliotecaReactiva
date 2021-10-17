package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
@Validated
public class RecomendarPorTipoYTematicaUseCase implements RecomendarPorTipoYTematica{
    private final RepositorioRecurso repositorioRecurso;
    private final MapperUtils mapperUtils;

    public RecomendarPorTipoYTematicaUseCase(RepositorioRecurso repositorioRecurso, MapperUtils mapperUtils) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get(String tipo, String tematica) {
        Objects.requireNonNull(tipo, "El tipo no puede estar vacio");
        Objects.requireNonNull(tematica, "La tematica no puede estar vacia");
        return repositorioRecurso.findAllByTipoAndTematica(tipo, tematica).map(mapperUtils.mapEntityToResource()).distinct();
    }
}
