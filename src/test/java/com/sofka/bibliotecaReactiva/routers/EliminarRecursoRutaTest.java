package com.sofka.bibliotecaReactiva.routers;

import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import com.sofka.bibliotecaReactiva.useCases.EliminarRecursoUseCase;
import com.sofka.bibliotecaReactiva.useCases.MapperUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EliminarRecursoRuta.class, EliminarRecursoUseCase.class, MapperUtils.class})
class EliminarRecursoRutaTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testEliminarRecuerso(){
        Mono<Void> voidReturn = Mono.empty();

        Mockito.when(repositorioRecurso.deleteById("1234")).thenReturn(voidReturn);
        webTestClient.delete().uri("/eliminar/{id}", "1234")
                .exchange()
                .expectStatus()
                .isAccepted();
    }

}