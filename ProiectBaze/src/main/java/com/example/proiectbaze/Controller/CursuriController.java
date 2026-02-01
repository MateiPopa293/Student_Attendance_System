/**
 * Controller pentru gestionarea cursurilor.
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.controller;

import com.example.proiectbaze.model.Cursuri;
import com.example.proiectbaze.repository.CursuriRepository;
import com.example.proiectbaze.repository.ProfesorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CursuriController {

    @Autowired
    private CursuriRepository cursuriRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping("/cursuri")
    public String listCursuri(Model model) {
        model.addAttribute("listaCursuri", cursuriRepository.findAll());
        return "cursuri";
    }

    @GetMapping("/cursuri/nou")
    public String showAddCursForm(Model model) {
        model.addAttribute("curs", new Cursuri());
        model.addAttribute("listaProfesori", profesorRepository.findAll());
        return "adaugare-curs";
    }


    @PostMapping("/cursuri/salveaza")
    public String saveCurs(@Valid @ModelAttribute("curs") Cursuri curs,
                           BindingResult bindingResult,
                           Model model) {

        // Dacă sunt erori (ex: semestru invalid)
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaProfesori", profesorRepository.findAll());
            return "adaugare-curs";
        }

        cursuriRepository.save(curs);
        return "redirect:/cursuri";
    }

    @GetMapping("/cursuri/editeaza/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Cursuri curs = cursuriRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curs Id:" + id));

        model.addAttribute("curs", curs);
        model.addAttribute("listaProfesori", profesorRepository.findAll());

        return "adaugare-curs";
    }
    
    @GetMapping("/cursuri/sterge/{id}")
    public String deleteCurs(@PathVariable("id") Long id) {
        cursuriRepository.deleteById(id);
        return "redirect:/cursuri";
    }
}