package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class PrestarUseCase implements Function<String, Mono<String>> {
    private final RepositorioRecurso repositorioRecurso;
    private final ActualizarRecursoUseCase actualizarRecursoUseCase;
    private final MapperUtils mapperUtils;

    public PrestarUseCase(RepositorioRecurso repositorioRecurso,  MapperUtils mapperUtils) {
        this.repositorioRecurso = repositorioRecurso;
        this.actualizarRecursoUseCase = new ActualizarRecursoUseCase(repositorioRecurso,mapperUtils);
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id,"El id no puede ser nulo");
        return repositorioRecurso.findById(id).flatMap(
                recurso -> {
                    if (recurso.getCantidadDisponible() > recurso.getCantidadPrestada()){
                        recurso.setFechaPrestamo(LocalDate.now());
                        recurso.setCantidadPrestada(recurso.getCantidadPrestada() + 1);
                        return actualizarRecursoUseCase.apply(mapperUtils.mapEntityToResource().apply(recurso)).thenReturn("El recurso " + recurso.getNombreRecurso() + " no esta disponible");
                    }
                    return Mono.just("El recurso " + recurso.getNombreRecurso() + " no cuenta con unidades disponibles");
                }
        );
    }
}
