package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import reactor.core.publisher.Mono;

public class GuardarRecursoUseCase implements GuardarRecurso{
    private final RepositorioRecurso repositorioRecurso;
    private final MapperUtils mapperUtils;

    public GuardarRecursoUseCase(RepositorioRecurso repositorioRecurso, MapperUtils mapperUtils) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        return repositorioRecurso
                .save(mapperUtils.mapperToResource().apply(recursoDTO))
                .map(recurso -> mapperUtils.mapEntityToResource().apply(recurso));
    }
}
