package com.academia.batch.service;

// @Service con inyeccion por constructor de EstudianteRepository.
// Metodo contarAprobados() que devuelve cuantos estudiantes tienen promedio >= 70,
// usando findAll() y un stream con filter y count.

import com.academia.batch.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }


    /**
     * Cuenta cuántos estudiantes tienen un promedio mayor o igual a 70.
     *
     * @return cantidad de estudiantes aprobados.
     */
    public long contarAprobados() {
        final double promedioMinimoAprobacion = 70;

        return estudianteRepository.findAll().stream()
                .filter(estudiante -> estudiante.getPromedio() >= promedioMinimoAprobacion)
                .count();
    }

    public long contarReprobados() {
        return estudianteRepository.findAll().stream()
                .filter(estudiante -> estudiante.getPromedio() < 70)
                .count();
    }
}
