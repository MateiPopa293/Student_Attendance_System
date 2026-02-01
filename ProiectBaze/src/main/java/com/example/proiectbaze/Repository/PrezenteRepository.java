/**
 * Repository pentru entitatea Prezente
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */


package com.example.proiectbaze.repository;

import com.example.proiectbaze.model.Prezente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PrezenteRepository extends JpaRepository<Prezente, Long> {

    //  INTEROGARE COMPLEXA CU PARAMETRU VARIABIL

    @Query("SELECT p.student, COUNT(p) as numarPrezente " +
            "FROM Prezente p " +
            "GROUP BY p.student " +
            "HAVING COUNT(p) >= :minPrezente " +
            "ORDER BY numarPrezente DESC")
    List<Object[]> findStudentiCuPrezenteMinime(@Param("minPrezente") Long minPrezente);

    // Notă: Returnăm List<Object[]> pentru că rezultatul conține
    // și Studentul (Obiect) și Numărul (Long), deci nu e o entitate simplă.
}