package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ActualizarRecursoUseCase implements GuardarRecurso{
    private final RepositorioRecurso repositorioRecurso;
    private final MapperUtils mapperUtils;

    public ActualizarRecursoUseCase(RepositorioRecurso repositorioRecurso, MapperUtils mapperUtils) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "Se requiere el Id del Recurso");
        return repositorioRecurso.save(mapperUtils.mapperToResource().apply(recursoDTO))
                .map(recurso -> mapperUtils.mapEntityToResource().apply(recurso));
    }
}
