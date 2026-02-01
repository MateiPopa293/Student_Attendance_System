/**
 * Clasa pentru gestionarea Inscrierilor (relația Student - Curs).
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "inscrieri")
public class Inscrieri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscriere")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_curs")
    private Cursuri curs;

    @NotBlank(message = "Anul universitar este obligatoriu")
    private String anUniversitar;
    public Inscrieri() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Cursuri getCurs() { return curs; }
    public void setCurs(Cursuri curs) { this.curs = curs; }
    public String getAnUniversitar() { return anUniversitar; }
    public void setAnUniversitar(String anUniversitar) { this.anUniversitar = anUniversitar; }
}