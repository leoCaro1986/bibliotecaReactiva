package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class ComprobarDisponibilidadUseCase implements Function<String, Mono<String>> {
    private final RepositorioRecurso repositorioRecurso;

    public ComprobarDisponibilidadUseCase(RepositorioRecurso repositorioRecurso) {
        this.repositorioRecurso = repositorioRecurso;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id,"El id no puede ser nulo");
        return repositorioRecurso.findById(id)
                .map(recurso ->
                        recurso.getCantidadDisponible() > recurso.getCantidadPrestada()
                                ? String.valueOf("El recurso " + recurso.getNombreRecurso() + " cuenta con " + (recurso.getCantidadDisponible() - recurso.getCantidadPrestada()) + " unidades disponibles")
                                : String.valueOf("El recurso " + recurso.getNombreRecurso() + "no se encuentra disponible")
        );
    }
}
