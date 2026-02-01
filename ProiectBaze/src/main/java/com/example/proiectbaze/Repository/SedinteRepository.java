/**
 * Repository pentru entitatea Sedinte.
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.repository;

import com.example.proiectbaze.model.Sedinte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SedinteRepository extends JpaRepository<Sedinte, Long> {

    @Query("SELECT s FROM Sedinte s LEFT JOIN FETCH s.curs")
    List<Sedinte> findAllWithDetails();

    // 2. COMPLEXĂ 4: Total prezențe grupate pe tipul ședinței
    @Query("SELECT s.tipul, COUNT(p) " +
            "FROM Sedinte s " +
            "JOIN s.prezente p " +
            "GROUP BY s.tipul")
    List<Object[]> findPrezentePerTip();
}