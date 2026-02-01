/**
 * Repository pentru entitatea Cursuri.
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.repository;

import com.example.proiectbaze.model.Cursuri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // IMPORT NOU
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursuriRepository extends JpaRepository<Cursuri, Long> {

    // COMPLEXĂ 2: Top Cursuri după numărul de înscrieri
    @Query("SELECT c.nume, COUNT(i) as nrStudenti " +
            "FROM Cursuri c " +
            "LEFT JOIN c.inscrieri i " +
            "GROUP BY c.id, c.nume " +
            "ORDER BY nrStudenti DESC")
    List<Object[]> findCursuriPopulare();

    // --- NOU: INTEROGARE SIMPLĂ CU JOIN ȘI PARAMETRU VARIABIL ---
    // Caută cursurile unde numele profesorului conține textul introdus
    @Query("SELECT c FROM Cursuri c JOIN c.profesor p WHERE LOWER(p.nume) LIKE LOWER(CONCAT('%', :numeProf, '%'))")
    List<Cursuri> findCursuriByNumeProfesor(@Param("numeProf") String numeProf);
}