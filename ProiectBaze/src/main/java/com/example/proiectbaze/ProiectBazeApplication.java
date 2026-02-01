package com.example.proiectbaze;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class ProiectBazeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProiectBazeApplication.class, args);
    }

    @Entity
    @Table(name = "sedinte")
    public static class Sedinte {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_sedinta")
        private Long id;

        @Temporal(TemporalType.TIMESTAMP)
        private Date data;

        private int durata;
        private String tipul; // "lab", "seminar", "curs"

        // Relație N:1 (Multe ședințe aparțin de 1 curs)
        @ManyToOne
        @JoinColumn(name = "id_curs")
        private Cursuri curs;

        // Relația inversă: O ședință are mai multe prezențe
        @OneToMany(mappedBy = "sedinta")
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

    @Entity
    @Table(name = "studenti")
    public static class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_student")
        private Long id;

        private String nume;
        private String prenume;
        private String email;
        private String grupa;
        private String seria;

        @OneToMany(mappedBy = "student")
        private Set<Inscrieri> inscrieri;

        @OneToMany(mappedBy = "student")
        private Set<Prezente> prezente;

        public Student() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNume() {
            return nume;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public String getPrenume() {
            return prenume;
        }

        public void setPrenume(String prenume) {
            this.prenume = prenume;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGrupa() {
            return grupa;
        }

        public void setGrupa(String grupa) {
            this.grupa = grupa;
        }

        public String getSeria() {
            return seria;
        }

        public void setSeria(String seria) {
            this.seria = seria;
        }

        public Set<Inscrieri> getInscrieri() {
            return inscrieri;
        }

        public void setInscrieri(Set<Inscrieri> inscrieri) {
            this.inscrieri = inscrieri;
        }

        public Set<Prezente> getPrezente() {
            return prezente;
        }

        public void setPrezente(Set<Prezente> prezente) {
            this.prezente = prezente;
        }
    }

    @Entity
    @Table(name = "profesori")
    public static class Profesor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_profesor")
        private Long id;

        private String nume;
        private String prenume;
        private String email;
        private String departament;

        @OneToMany(mappedBy = "profesor")
        private Set<Cursuri> cursuri;

        public Profesor() {}

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNume() {
            return nume;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public String getPrenume() {
            return prenume;
        }

        public void setPrenume(String prenume) {
            this.prenume = prenume;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDepartament() {
            return departament;
        }

        public void setDepartament(String departament) {
            this.departament = departament;
        }

        public Set<Cursuri> getCursuri() {
            return cursuri;
        }

        public void setCursuri(Set<Cursuri> cursuri) {
            this.cursuri = cursuri;
        }
    }

    @Entity
    @Table(name = "prezente")
    public static class Prezente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_prezenta")
        private Long id;

        // Relație N:1 -> "id_student" (FK)
        @ManyToOne
        @JoinColumn(name = "id_student")
        private Student student;

        // Relație N:1 -> "id_sedinta" (FK)
        @ManyToOne
        @JoinColumn(name = "id_sedinta")
        private Sedinte sedinta;

        private String status; // "prezent", "absent", etc.

    }

    @Entity
    @Table(name = "inscrieri")
    public static class Inscrieri {

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

        private String anUniversitar;

    }

    @Entity
    @Table(name = "cursuri")
    public static class Cursuri {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_curs")
        private Long id;

        private String nume;
        private String descriere;
        private String semestru;

        // Relație N:1 (Multe cursuri sunt ținute de 1 profesor)
        @ManyToOne
        @JoinColumn(name = "id_profesor") // FK din diagrama ta
        private Profesor profesor;

        // Relația inversă: Un curs are mai multe înscrieri
        @OneToMany(mappedBy = "curs")
        private Set<Inscrieri> inscrieri;

        // Relația inversă: Un curs are mai multe ședințe
        @OneToMany(mappedBy = "curs")
        private Set<Sedinte> sedinte;

    }
}
