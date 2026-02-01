/**
 * Clasa pentru gestionarea cursurilor.
 * Include validare pentru semestru (doar o cifră).
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "cursuri")
public class Cursuri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curs")
    private Long id;

    @NotBlank(message = "Numele cursului este obligatoriu")
    private String nume;

    private String descriere;

    // "^\\d$" conditie pentru o singura cifra
    @NotBlank(message = "Semestrul este obligatoriu")
    @Pattern(regexp = "^\\d$", message = "Semestrul trebuie să fie o singură cifră (ex: 1, 2)!")
    private String semestru;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @OneToMany(mappedBy = "curs", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inscrieri> inscrieri;

    @OneToMany(mappedBy = "curs", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sedinte> sedinte;

    public Cursuri() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getDescriere() { return descriere; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public String getSemestru() { return semestru; }
    public void setSemestru(String semestru) { this.semestru = semestru; }
    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }
    public Set<Inscrieri> getInscrieri() { return inscrieri; }
    public void setInscrieri(Set<Inscrieri> inscrieri) { this.inscrieri = inscrieri; }
    public Set<Sedinte> getSedinte() { return sedinte; }
    public void setSedinte(Set<Sedinte> sedinte) { this.sedinte = sedinte; }
}