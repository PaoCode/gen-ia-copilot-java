package com.academia.batch.controller;

// @RestController en /api/reportes que usa ReporteRepository:
// GET / lista todos los reportes; GET /estado/{estado} devuelve los que tengan ese estado
// (convertido a mayusculas) usando findByEstado.

import com.academia.batch.repository.ReporteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.academia.batch.model.EstudianteReporte;
import java.util.List;


@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteRepository reporteRepository;

    public ReporteController(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @GetMapping
    public ResponseEntity<List<EstudianteReporte>> listarTodos() {
        return ResponseEntity.ok(reporteRepository.findAll());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<EstudianteReporte>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(reporteRepository.findByEstado(estado.toUpperCase()));
    }
}
