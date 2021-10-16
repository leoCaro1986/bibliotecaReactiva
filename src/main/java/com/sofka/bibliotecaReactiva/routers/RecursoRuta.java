package com.sofka.bibliotecaReactiva.routers;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.useCases.GuardarRecursoUseCase;
import com.sofka.bibliotecaReactiva.useCases.ListaRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecursoRuta {
    @Bean
    public RouterFunction<ServerResponse> traerTodos(ListaRecursoUseCase listaRecursoUseCase) {
        return route(GET("/recursos"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listaRecursoUseCase.get(), RecursoDTO.class)
                )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> guardar(GuardarRecursoUseCase guardarRecursoUseCase) {
        Function<RecursoDTO, Mono<ServerResponse>> exjecutor = recursoDTO -> guardarRecursoUseCase.apply(recursoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(
                POST("/recursos/agregar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class).flatMap(exjecutor)
        );
    }

}
