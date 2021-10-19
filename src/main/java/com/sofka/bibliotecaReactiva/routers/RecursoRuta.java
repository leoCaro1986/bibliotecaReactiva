package com.sofka.bibliotecaReactiva.routers;

import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.useCases.*;
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
    public RouterFunction<ServerResponse> actualizar(ActualizarRecursoUseCase actualizarRecursoUseCase){
        Function<RecursoDTO, Mono<ServerResponse>> executor = recursoDTO -> actualizarRecursoUseCase.apply(recursoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/recursos/editar")
                        .and(accept(MediaType.APPLICATION_JSON)), request -> request
                        .bodyToMono(RecursoDTO.class)
                        .flatMap(executor)
        );
    }



    @Bean
    public RouterFunction<ServerResponse> disponibilidad(ComprobarDisponibilidadUseCase comprobarDisponibilidadUseCase){
        return route(
                GET("/recursos/disponibilidad/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(comprobarDisponibilidadUseCase.apply(request.pathVariable("id")), String.class)
                        ).onErrorResume((Error)-> ServerResponse.badRequest().build())
        );
    }


    @Bean
    public RouterFunction<ServerResponse> prestar(PrestarUseCase prestarUseCase){
        return  route(
                PUT("/recursos/prestar/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(prestarUseCase.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }


    @Bean
    public RouterFunction<ServerResponse> recomendarPorTematica(RecomendarPorTematicaUseCase recomendarPorTematicaUseCase){
        return route(
                GET("/recursos/recomendarportema/{tema}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTematicaUseCase.get(request.pathVariable("tema")), RecursoDTO.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }


    @Bean
    public RouterFunction<ServerResponse> recomendarPorTipo(RecomendarPorTipoUseCase recomendarPorTipoUseCase){
        return route(
                GET("/recursos/recomendarportema/{tipo}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTipoUseCase.get(request.pathVariable("tipo")), RecursoDTO.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }


    @Bean
    public RouterFunction<ServerResponse> recomendarPorTipoYTematica(RecomendarPorTipoYTematicaUseCase recomendarPorTipoYTematicaUseCase ){
        return route(
                GET("/recursos/recomendarportipoytema/{tipo}/{tema}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTipoYTematicaUseCase.get(request.pathVariable("tipo"), request.pathVariable("tema")), RecursoDTO.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }


    @Bean
    public RouterFunction<ServerResponse> regresarRecurso(RegresarRecursoUseCase regresarRecursoUseCase){
        return route(
                GET("/recursos/devolver/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(regresarRecursoUseCase.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
}
