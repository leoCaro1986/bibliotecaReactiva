package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import com.sofka.bibliotecaReactiva.repositories.RepositorioRecurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@SpringBootTest
class ListaRecursoUseCaseTest {
    RepositorioRecurso repositorioRecurso;
    ListaRecursoUseCase listaRecursoUseCase;



    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        repositorioRecurso = mock(RepositorioRecurso.class);
        listaRecursoUseCase = new ListaRecursoUseCase(repositorioRecurso, mapperUtils);
    }

    @Test
    @DisplayName("test para obtener los datos de un recurso")
    void getValidationTest(){
        var recurso = new Recurso();
        recurso.setId("1234");
        recurso.setNombreRecurso("el olvido");
        recurso.setTematica("poesia");
        recurso.setTipo("libro");
        recurso.setFechaPrestamo(LocalDate.parse("2020-01-10"));
        recurso.setCantidadDisponible(3);
        recurso.setCantidadPrestada(2);
        when(repositorioRecurso.findAll()).thenReturn(Flux.just(recurso));

        StepVerifier.create(listaRecursoUseCase.get())
                .expectNextMatches(recursoDTO -> {
                    assert recursoDTO.getId().equals("1234");
                    assert recursoDTO.getNombreRecurso().equals("el olvido");
                    assert recursoDTO.getTematica().equals("poesia");
                    assert recursoDTO.getTipo().equals("libro");
                    assert recursoDTO.getFechaPrestamo().equals(LocalDate.parse("2020-01-10"));
                    assert recursoDTO.getCantidadDisponible().equals(3);
                    assert recursoDTO.getCantidadPrestada().equals(2);
                    return true;
                })
                .verifyComplete();

        verify(repositorioRecurso).findAll();
    }
}