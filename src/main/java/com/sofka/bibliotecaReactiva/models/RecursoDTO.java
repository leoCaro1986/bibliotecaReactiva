package com.sofka.bibliotecaReactiva.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecursoDTO {
    private String id;
    @NotBlank(message = "Debe completar el campo nombre del recurso")
    private String nombreRecurso;
    @NotBlank(message = "Por favor digite la tematica de recurso")
    private String tematicaRecurso;
    @NotBlank(message = "Por favor digite el tipo de recurso")
    private String tipoRecurso;
    private LocalDate fechaPrestamo;
    @NotNull(message = "Debe ingresar la cantidad del recurso")
    private Integer cantidadDisponible;
    private Integer cantidadPrestada;
}
