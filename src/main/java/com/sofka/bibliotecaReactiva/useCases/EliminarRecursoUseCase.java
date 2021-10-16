package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarRecursoUseCase implements Function<String, Mono<Void>> {
    private final RepositorioRecurso repositorioRecurso;

    public EliminarRecursoUseCase(RepositorioRecurso repositorioRecurso) {
        this.repositorioRecurso = repositorioRecurso;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id,"Se requiere el Id");
        return repositorioRecurso.deleteById(id);
    }
}
