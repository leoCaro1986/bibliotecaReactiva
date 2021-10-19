package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;
@Service
@Validated
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
