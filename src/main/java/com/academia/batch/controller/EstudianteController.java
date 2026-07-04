package com.academia.batch.controller;

import com.academia.batch.repository.EstudianteEntity;
import com.academia.batch.repository.EstudianteRepository;
import com.academia.batch.service.EstudianteService;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes") //url base a la que accederemos es decir localhost:8080/api/estudiantes
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteService estudianteService;

    // aqui es la inyeccion de las dependencias de los repositorios y servicios que se requieren
    public EstudianteController(EstudianteRepository estudianteRepository, EstudianteService estudianteService) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteService = estudianteService;
    }

    @GetMapping //muestra la lista de todos los estudiantes
    public ResponseEntity<List<EstudianteEntity>> listarTodos() {
        return ResponseEntity.ok(estudianteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteEntity> obtenerPorId(@PathVariable Long id) {
        return estudianteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/aprobados/total")
    public ResponseEntity<Map<String, Long>> totalAprobados() {
        return ResponseEntity.ok(Map.of("total", estudianteService.contarAprobados()));
    }

    @PostMapping
    public ResponseEntity<EstudianteEntity> crear(@RequestBody EstudianteEntity estudiante) {
        EstudianteEntity guardado = estudianteRepository.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create("/api/estudiantes/" + guardado.getId()))
                .body(guardado);
    }

    @PutMapping("/{id}") // encuentra un estudiante por su id y lo modifica exitosamente obteniendo un 200 y si no lo encuentra devuelve un 404
    public ResponseEntity<EstudianteEntity> reemplazar(@PathVariable Long id,
                                                       @RequestBody EstudianteEntity estudiante) {
        return estudianteRepository.findById(id)
                .map(existing -> {
                    estudiante.setId(existing.getId());
                    return ResponseEntity.ok(estudianteRepository.save(estudiante));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstudianteEntity> cambiarGrupo(@PathVariable Long id,
                                                         @RequestBody Map<String, Object> payload) {
        Object grupo = payload.get("grupo");
        if (grupo == null) {
            return ResponseEntity.badRequest().build();
        }

        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setGrupo(String.valueOf(grupo));
                    return ResponseEntity.ok(estudianteRepository.save(estudiante));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // /explain encuentra un estudiante por su id y lo elimina exitosamente obteniendo un 204 y si no lo encuentra devuelve un 404
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!estudianteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        estudianteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
