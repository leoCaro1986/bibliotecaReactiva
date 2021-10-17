package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class RecomendarPorTipoUseCase implements RecomendarPorTipo{
    private final RepositorioRecurso repositorioRecurso;
    private final MapperUtils mapperUtils;

    public RecomendarPorTipoUseCase(RepositorioRecurso repositorioRecurso, MapperUtils mapperUtils) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Flux<RecursoDTO> get(String tipoRecurso) {
        return repositorioRecurso.findAllByTipo(tipoRecurso).map(mapperUtils.mapEntityToResource());
    }
}
