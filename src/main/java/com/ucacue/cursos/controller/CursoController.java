package com.ucacue.cursos.controller;

import com.ucacue.cursos.entity.Curso;
import com.ucacue.cursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Listar todos o filtrar por área: /api/cursos?area=Ingeniería
    @GetMapping
    public ResponseEntity<List<Curso>> listar(@RequestParam(required = false) String area) {
        return ResponseEntity.ok(cursoService.listarTodos(area));
    }

    // Detalle
    @GetMapping("/{id}")
    public ResponseEntity<Curso> detalle(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }

    // Crear
    @PostMapping
    public ResponseEntity<Curso> crear(@Valid @RequestBody Curso curso) {
        Curso creado = cursoService.crear(curso);
        return ResponseEntity.status(201).body(creado);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.actualizar(id, curso));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        Map<String, String> body = new HashMap<>();
        body.put("mensaje", "Curso eliminado correctamente");
        return ResponseEntity.ok(body);
    }

    // Promedio de créditos: /api/cursos/promedio-creditos
    @GetMapping("/promedio-creditos")
    public ResponseEntity<Map<String, Object>> promedioCreditos() {
        double promedio = cursoService.promedioCreditos();
        Map<String, Object> body = new HashMap<>();
        body.put("promedioCreditos", promedio);
        return ResponseEntity.ok(body);
    }
}
