package com.ucacue.cursos.repository;

import com.ucacue.cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByAreaIgnoreCase(String area);

    @Query("SELECT AVG(c.creditos) FROM Curso c")
    Double promedioCreditos();

    @Query("SELECT c FROM Curso c WHERE (:area IS NULL OR LOWER(c.area) = LOWER(:area))")
    List<Curso> buscarPorAreaOpcional(@Param("area") String area);
}
