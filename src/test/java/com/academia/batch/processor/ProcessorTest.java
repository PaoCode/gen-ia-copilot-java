package com.academia.batch.processor;
import com.academia.batch.model.Estudiante;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ProcessorTest {
        private final EstudianteProcessor processor = new EstudianteProcessor();

        @Test
        void process_calculaCorrectamenteElPromedio() throws Exception {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre("Ana");
            estudiante.setGrupo("A");
            estudiante.setNota1(70);
            estudiante.setNota2(80);
            estudiante.setNota3(90);

            Estudiante resultado = processor.process(estudiante);

            assertEquals(80.0, resultado.getPromedio(), 0.0001);
        }

    }

