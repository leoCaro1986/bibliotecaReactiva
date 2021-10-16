package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import reactor.core.publisher.Flux;

public class RecomendarPorTipoUseCase implements RecomendarPorTipo{
    private final RepositorioRecurso repositorioRecurso;
    private final MapperUtils mapperUtils;

    public RecomendarPorTipoUseCase(RepositorioRecurso repositorioRecurso, MapperUtils mapperUtils) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Flux<RecursoDTO> get(String tipoRecurso) {
        return repositorioRecurso.findAllBytipoRecurso(tipoRecurso).map(mapperUtils.mapEntityToResource());
    }
}
