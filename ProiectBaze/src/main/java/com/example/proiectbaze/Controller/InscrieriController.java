/**
 * Controller pentru gestionarea Inscrierilor
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */


package com.example.proiectbaze.controller;

import com.example.proiectbaze.model.Inscrieri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate; // IMPORT pentru data curenta, ai sa facem butonul de and universitar
import java.util.ArrayList;
import java.util.List;

@Controller
public class InscrieriController {

    @Autowired
    private com.example.proiectbaze.repository.InscrieriRepository inscrieriRepository;

    @Autowired
    private com.example.proiectbaze.repository.StudentRepository studentRepository;

    @Autowired
    private com.example.proiectbaze.repository.CursuriRepository cursuriRepository;


    private List<String> getAniUniversitari() {
        List<String> ani = new ArrayList<>();

        int anCurent = LocalDate.now().getYear();


        for (int i = -1; i < 3; i++) {
            int start = anCurent + i;
            int end = start + 1;
            ani.add(start + "-" + end);
        }

        return ani;
    }


    @GetMapping("/inscrieri")
    public String listInscrieri(Model model) {
        model.addAttribute("listaInscrieri", inscrieriRepository.findAll());
        return "inscrieri";
    }


    @GetMapping("/inscrieri/nou")
    public String showAddInscriereForm(Model model) {
        model.addAttribute("inscriere", new Inscrieri());

        model.addAttribute("listaStudenti", studentRepository.findAll());
        model.addAttribute("listaCursuri", cursuriRepository.findAll());

        //  METODA DINAMICĂ
        model.addAttribute("listaAni", getAniUniversitari());

        return "adaugare-inscriere";
    }


    @PostMapping("/inscrieri/salveaza")
    public String saveInscriere(@ModelAttribute("inscriere") Inscrieri inscriere) {
        inscrieriRepository.save(inscriere);
        return "redirect:/inscrieri";
    }


    @GetMapping("/inscrieri/editeaza/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Inscrieri inscriere = inscrieriRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inscriere Id:" + id));

        model.addAttribute("inscriere", inscriere);

        model.addAttribute("listaStudenti", studentRepository.findAll());
        model.addAttribute("listaCursuri", cursuriRepository.findAll());

        // ȘI AICI APELĂM METODA DINAMICĂ
        model.addAttribute("listaAni", getAniUniversitari());

        return "adaugare-inscriere";
    }


    @GetMapping("/inscrieri/sterge/{id}")
    public String deleteInscriere(@PathVariable("id") Long id) {
        inscrieriRepository.deleteById(id);
        return "redirect:/inscrieri";
    }
}