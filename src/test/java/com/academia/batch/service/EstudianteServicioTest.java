package com.academia.batch.service;

import com.academia.batch.repository.EstudianteEntity;
import com.academia.batch.repository.EstudianteRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    @Test
    void contarAprobados_devuelveDosCuandoHayDosAprobadosYUnReprobado() {
        EstudianteEntity e1 = new EstudianteEntity();
        e1.setPromedio(90.0);

        EstudianteEntity e2 = new EstudianteEntity();
        e2.setPromedio(70.0);

        EstudianteEntity e3 = new EstudianteEntity();
        e3.setPromedio(69.9);

        List<EstudianteEntity> estudiantes = new ArrayList<>();
        estudiantes.add(e1);
        estudiantes.add(e2);
        estudiantes.add(e3);

        when(estudianteRepository.findAll()).thenReturn(estudiantes);

        long resultado = estudianteService.contarAprobados();

        assertEquals(2L, resultado);
    }
}