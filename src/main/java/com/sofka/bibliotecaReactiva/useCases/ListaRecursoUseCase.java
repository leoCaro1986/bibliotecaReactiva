package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListaRecursoUseCase implements Supplier<Flux<RecursoDTO>> {
    private final RepositorioRecurso repositorioRecurso;
    private final MapperUtils mapperUtils;

    public ListaRecursoUseCase(RepositorioRecurso repositorioRecurso, MapperUtils mapperUtils) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Flux<RecursoDTO> get() {

        return repositorioRecurso.findAll()
                .map(mapperUtils.mapEntityToResource());
    }
}
