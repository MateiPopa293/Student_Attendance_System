/**
 * Controller pentru sedinte
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */


package com.example.proiectbaze.controller;

import com.example.proiectbaze.model.Sedinte;
import com.example.proiectbaze.repository.CursuriRepository;
import com.example.proiectbaze.repository.SedinteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SedinteController {

    @Autowired
    private SedinteRepository sedinteRepository;

    @Autowired
    private CursuriRepository cursuriRepository;


    @GetMapping("/sedinte")
    public String listSedinte(Model model) {
        model.addAttribute("listaSedinte", sedinteRepository.findAll());
        return "sedinte";
    }

    @GetMapping("/sedinte/nou")
    public String showAddSedintaForm(Model model) {
        model.addAttribute("sedinta", new Sedinte());
        model.addAttribute("listaCursuri", cursuriRepository.findAll());
        return "adaugare-sedinta";
    }

    @PostMapping("/sedinte/salveaza")
    public String saveSedinta(@ModelAttribute("sedinta") Sedinte sedinta) {
        sedinteRepository.save(sedinta);
        return "redirect:/sedinte";
    }

    @GetMapping("/sedinte/editeaza/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Sedinte sedinta = sedinteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sedinta Id:" + id));

        model.addAttribute("sedinta", sedinta);
        model.addAttribute("listaCursuri", cursuriRepository.findAll());

        return "adaugare-sedinta";
    }

    @GetMapping("/sedinte/sterge/{id}")
    public String deleteSedinta(@PathVariable("id") Long id) {
        sedinteRepository.deleteById(id);
        return "redirect:/sedinte";
    }
}