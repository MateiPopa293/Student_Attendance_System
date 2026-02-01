/**
 * Repository pentru entitatea Inscrieri.
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.repository;


import com.example.proiectbaze.model.Inscrieri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscrieriRepository extends JpaRepository<Inscrieri, Long> {

    /**
     * Aceasta este o interogare personalizată care înlocuiește findAll().
     * "LEFT JOIN FETCH" îi spune lui Hibernate să încarce "forțat"
     * datele studentului și ale cursului în aceeași interogare.
     * Asta previne eroarea 'LazyInitializationException'.
     */
    @Query("SELECT i FROM Inscrieri i LEFT JOIN FETCH i.student LEFT JOIN FETCH i.curs")
    List<Inscrieri> findAllWithDetails();
}