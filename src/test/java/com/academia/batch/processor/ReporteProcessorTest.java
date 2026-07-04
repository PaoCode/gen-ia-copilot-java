package com.academia.batch.processor;

import com.academia.batch.model.Estudiante;
import com.academia.batch.model.EstudianteReporte;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReporteProcessorTest {
    private final ReporteEstudianteProcessor processor = new ReporteEstudianteProcessor();

    @ParameterizedTest
    @CsvSource({
            "70.0, APROBADO",
            "69.9, REPROBADO"
    })
    void process_asignaEstadoSegunPromedio(double promedio, String esperado) throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Ana");
        estudiante.setGrupo("A");
        estudiante.setPromedio(promedio);

        EstudianteReporte reporte = processor.process(estudiante);

        assertEquals(esperado, reporte.getEstado());
    }
}
