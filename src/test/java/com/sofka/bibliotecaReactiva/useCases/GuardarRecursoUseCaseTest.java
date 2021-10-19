package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import com.sofka.bibliotecaReactiva.routers.CrearRecursoRouter;
import com.sofka.bibliotecaReactiva.routers.RecursoRuta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrearRecursoRouter.class, GuardarRecursoUseCase.class, MapperUtils.class})
class GuardarRecursoUseCaseTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testRouterCrearRecurso(){

        Recurso recurso = new Recurso();
        recurso.setId("1234");
        recurso.setNombreRecurso("el olvido");
        recurso.setTematica("poesia");
        recurso.setTipo("libro");
        recurso.setFechaPrestamo(LocalDate.parse("2020-01-10"));
        recurso.setCantidadDisponible(3);
        recurso.setCantidadPrestada(2);

        RecursoDTO recursoDTO = new RecursoDTO(recurso.getId(),
                recurso.getNombreRecurso(),recurso.getTematica(),recurso.getTipo(),recurso.getFechaPrestamo(),
                recurso.getCantidadDisponible(), recurso.getCantidadPrestada());

        Mono<Recurso> recursoMono = Mono.just(recurso);
        when(repositorioRecurso.save(any())).thenReturn(recursoMono);

        webTestClient.post()
                .uri("/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk();
    }
}