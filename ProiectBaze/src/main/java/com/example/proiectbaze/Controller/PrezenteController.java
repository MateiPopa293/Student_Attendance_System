/**
 * Controller pentru gestionarea prezentelor
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */

package com.example.proiectbaze.controller;

import com.example.proiectbaze.model.Prezente;
import com.example.proiectbaze.repository.PrezenteRepository;
import com.example.proiectbaze.repository.SedinteRepository;
import com.example.proiectbaze.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PrezenteController {

    @Autowired
    private PrezenteRepository prezenteRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SedinteRepository sedinteRepository;


    @GetMapping("/prezente")
    public String listPrezente(Model model) {
        model.addAttribute("listaPrezente", prezenteRepository.findAll());
        return "prezente";
    }


    @GetMapping("/prezente/nou")
    public String showAddPrezentaForm(Model model) {
        model.addAttribute("prezenta", new Prezente());
        model.addAttribute("listaStudenti", studentRepository.findAll());
        model.addAttribute("listaSedinte", sedinteRepository.findAll());

        return "adaugare-prezenta";
    }


    @PostMapping("/prezente/salveaza")
    public String savePrezenta(@ModelAttribute("prezenta") Prezente prezenta) {
        prezenteRepository.save(prezenta);
        return "redirect:/prezente";
    }

    @GetMapping("/prezente/editeaza/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Prezente prezenta = prezenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid prezenta Id:" + id));

        model.addAttribute("prezenta", prezenta);
        model.addAttribute("listaStudenti", studentRepository.findAll());
        model.addAttribute("listaSedinte", sedinteRepository.findAll());

        return "adaugare-prezenta";
    }

    @GetMapping("/prezente/sterge/{id}")
    public String deletePrezenta(@PathVariable("id") Long id) {
        prezenteRepository.deleteById(id);
        return "redirect:/prezente";
    }
}