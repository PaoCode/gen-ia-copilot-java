package com.academia.batch.processor;

// Processor de SpringBatch que implementa ItemProcessor<Estudiante,Estudiante>
// En el metodo process: calcula el promedio como (nota1 + nota2 + nota3) / 3,
// asigna el promedio al estudiante SetPromedio, registra un log con SLF4J
// "Step 1 - Procesando: {estudiante}" y devuelve el estudiante.

import com.academia.batch.model.Estudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

//@Component
public class EstudianteProcessor implements ItemProcessor<Estudiante, Estudiante> {

    private static final Logger log = LoggerFactory.getLogger(EstudianteProcessor.class);

    @Override
    public Estudiante process(Estudiante estudiante) throws Exception {
        double promedio = (estudiante.getNota1() + estudiante.getNota2() + estudiante.getNota3()) / 3;
        estudiante.setPromedio(promedio);
        System.out.println("Step 1 - Procesando: " + estudiante);
        return estudiante;
    } 

}
