/** Clasa pentru gestionarea Sedintelor
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */

package com.example.proiectbaze.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sedinte")
public class Sedinte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sedinta")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // formularul HTML
    private Date data;

    private int durata;
    private String tipul; // "lab", "seminar", "curs"

    @ManyToOne
    @JoinColumn(name = "id_curs")
    private Cursuri curs;

    @OneToMany(mappedBy = "sedinta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prezente> prezente;
    public Sedinte() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getTipul() {
        return tipul;
    }

    public void setTipul(String tipul) {
        this.tipul = tipul;
    }

    public Cursuri getCurs() {
        return curs;
    }

    public void setCurs(Cursuri curs) {
        this.curs = curs;
    }

    public Set<Prezente> getPrezente() {
        return prezente;
    }

    public void setPrezente(Set<Prezente> prezente) {
        this.prezente = prezente;
    }
}