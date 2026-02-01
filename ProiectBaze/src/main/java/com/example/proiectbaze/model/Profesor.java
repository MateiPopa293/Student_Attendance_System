/**
 * Clasa pentru gestionarea clasei Profesor.
 * Include validări pentru datele personale și departament.
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
@Table(name = "profesori")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long id;

    // VALIDARE: Doar litere, spații și cratime
    @NotBlank(message = "Numele este obligatoriu")
    @Pattern(regexp = "^[a-zA-ZăâîșțĂÂÎȘȚ\\s\\-]+$", message = "Numele poate conține doar litere!")
    private String nume;

    @NotBlank(message = "Prenumele este obligatoriu")
    @Pattern(regexp = "^[a-zA-ZăâîșțĂÂÎȘȚ\\s\\-]+$", message = "Prenumele poate conține doar litere!")
    private String prenume;

    @NotBlank(message = "Email-ul este obligatoriu")
    private String email;

    @NotBlank(message = "Departamentul este obligatoriu")
    private String departament;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private Set<Cursuri> cursuri;

    public Profesor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartament() { return departament; }
    public void setDepartament(String departament) { this.departament = departament; }
    public Set<Cursuri> getCursuri() { return cursuri; }
    public void setCursuri(Set<Cursuri> cursuri) { this.cursuri = cursuri; }
}