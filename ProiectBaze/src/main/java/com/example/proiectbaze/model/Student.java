/**
 * Clasa pentru gestionarea Clasei Studenti
 * Include validări pentru nume (doar litere) și grupă (doar cifre).
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "studenti")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long id;
    @NotBlank(message = "Numele este obligatoriu")
    @Pattern(regexp = "^[a-zA-ZăâîșțĂÂÎȘȚ\\s\\-]+$", message = "Numele poate conține doar litere!")
    private String nume;

    @NotBlank(message = "Prenumele este obligatoriu")
    @Pattern(regexp = "^[a-zA-ZăâîșțĂÂÎȘȚ\\s\\-]+$", message = "Prenumele poate conține doar litere!")
    private String prenume;

    private String email;
    @NotBlank(message = "Grupa este obligatorie")
    @Pattern(regexp = "^\\d+$", message = "Grupa trebuie să conțină doar cifre!")
    private String grupa;

    private String seria;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inscrieri> inscrieri;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prezente> prezente;

    public Student() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGrupa() { return grupa; }
    public void setGrupa(String grupa) { this.grupa = grupa; }
    public String getSeria() { return seria; }
    public void setSeria(String seria) { this.seria = seria; }
    public Set<Inscrieri> getInscrieri() { return inscrieri; }
    public void setInscrieri(Set<Inscrieri> inscrieri) { this.inscrieri = inscrieri; }
    public Set<Prezente> getPrezente() { return prezente; }
    public void setPrezente(Set<Prezente> prezente) { this.prezente = prezente; }
}