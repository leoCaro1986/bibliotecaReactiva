package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class RecomendarPorTematicaUseCase implements RecomendarPorTematica {
    private final RepositorioRecurso repositorioRecurso;
    private final MapperUtils mapperUtils;

    public RecomendarPorTematicaUseCase(RepositorioRecurso repositorioRecurso, MapperUtils mapperUtils) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get(String tematicaRecurso) {
        return repositorioRecurso.findAllByTematica(tematicaRecurso).map(mapperUtils.mapEntityToResource());
    }
}
