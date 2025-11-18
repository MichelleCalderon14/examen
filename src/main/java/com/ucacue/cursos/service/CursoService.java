package com.ucacue.cursos.service;

import com.ucacue.cursos.entity.Curso;
import com.ucacue.cursos.exception.CursoNotFoundException;
import com.ucacue.cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodos(String area) {
        if (area != null && !area.isBlank()) {
            return cursoRepository.findByAreaIgnoreCase(area);
        }
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso con id " + id + " no encontrado"));
    }

    public Curso crear(Curso curso) {
        curso.setId(null);
        return cursoRepository.save(curso);
    }

    public Curso actualizar(Long id, Curso datos) {
        Curso existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setCreditos(datos.getCreditos());
        existente.setArea(datos.getArea());
        return cursoRepository.save(existente);
    }

    public void eliminar(Long id) {
        Curso existente = buscarPorId(id);
        cursoRepository.delete(existente);
    }

    public double promedioCreditos() {
        Double promedio = cursoRepository.promedioCreditos();
        return promedio != null ? promedio : 0.0;
    }
}
