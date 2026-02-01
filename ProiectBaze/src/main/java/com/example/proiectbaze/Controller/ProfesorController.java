/**
 * Controller pentru gestionarea cadrelor didactice.
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.controller;

import com.example.proiectbaze.model.Profesor;
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
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping("/profesori")
    public String listProfesori(Model model) {
        model.addAttribute("listaProfesori", profesorRepository.findAll());
        return "profesori";
    }

    @GetMapping("/profesori/nou")
    public String showAddProfesorForm(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "adaugare-profesor";
    }

    @PostMapping("/profesori/salveaza")
    public String saveProfesor(@Valid @ModelAttribute("profesor") Profesor profesor,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "adaugare-profesor";
        }

        profesorRepository.save(profesor);
        return "redirect:/profesori";
    }

    @GetMapping("/profesori/editeaza/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profesor Id:" + id));

        model.addAttribute("profesor", profesor);
        return "adaugare-profesor";
    }

    @GetMapping("/profesori/sterge/{id}")
    public String deleteProfesor(@PathVariable("id") Long id) {
        profesorRepository.deleteById(id);
        return "redirect:/profesori";
    }
}