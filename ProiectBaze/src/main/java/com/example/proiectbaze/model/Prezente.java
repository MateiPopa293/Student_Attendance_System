/**
 * Clasa pentru gestionarea clasei Prezente
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */



package com.example.proiectbaze.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prezente")
public class Prezente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prezenta")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_sedinta")
    private Sedinte sedinta;

    private String status; // "prezent", "absent"

    public Prezente() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Sedinte getSedinta() {
        return sedinta;
    }

    public void setSedinta(Sedinte sedinta) {
        this.sedinta = sedinta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}