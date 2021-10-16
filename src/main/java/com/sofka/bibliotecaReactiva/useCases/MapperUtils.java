package com.sofka.bibliotecaReactiva.useCases;

import com.sofka.bibliotecaReactiva.collections.Recurso;
import com.sofka.bibliotecaReactiva.models.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {
    public Function<RecursoDTO, Recurso> mapperToResource(){
        return actualizarRecurso ->{
            var recurso = new Recurso();
            recurso.setId(actualizarRecurso.getId());
            recurso.setNombreRecurso(actualizarRecurso.getNombreRecurso());
            recurso.setTematicaRecurso(actualizarRecurso.getTematicaRecurso());
            recurso.setTipoRecurso(actualizarRecurso.getTipoRecurso());
            recurso.setFechaPrestamo(actualizarRecurso.getFechaPrestamo());
            recurso.setCantidadDisponible(actualizarRecurso.getCantidadDisponible());
            recurso.setCantidadPrestada(actualizarRecurso.getCantidadPrestada());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapEntityToResource(){
        return entity -> new RecursoDTO(
                entity.getId(),
                entity.getNombreRecurso(),
                entity.getTematicaRecurso(),
                entity.getTipoRecurso(),
                entity.getFechaPrestamo(),
                entity.getCantidadDisponible(),
                entity.getCantidadPrestada()
        );
    }
}
