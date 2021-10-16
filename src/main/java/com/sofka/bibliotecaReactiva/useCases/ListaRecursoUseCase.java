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

    public ListaRecursoUseCase(RepositorioRecurso repositorioRecurso) {
        this.repositorioRecurso = repositorioRecurso;
    }


    @Override
    public Flux<RecursoDTO> get() {
        return null;
    }
}
