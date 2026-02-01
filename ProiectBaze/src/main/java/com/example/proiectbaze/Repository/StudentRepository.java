/**
 * Repository pentru entitatea Student
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */

package com.example.proiectbaze.repository;

import com.example.proiectbaze.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}