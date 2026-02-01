/**
 * Repository pentru entitatea Profesor.
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.repository;

import com.example.proiectbaze.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    // COMPLEXĂ 3: Câte cursuri are fiecare profesorr
    @Query("SELECT p.nume, p.prenume, COUNT(c) as nrCursuri " +
            "FROM Profesor p " +
            "LEFT JOIN p.cursuri c " +
            "GROUP BY p.id, p.nume, p.prenume " +
            "ORDER BY nrCursuri DESC")
    List<Object[]> findStatisticiProfesori();
}