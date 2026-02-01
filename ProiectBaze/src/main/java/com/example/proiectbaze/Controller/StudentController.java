/**
 * Controller pentru gestionarea operațiunilor asupra studenților.
 *
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.controller;

import com.example.proiectbaze.model.Student;
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
public class StudentController {

    @Autowired
    private com.example.proiectbaze.repository.StudentRepository studentRepository;

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/studenti")
    public String listStudenti(Model model) {
        model.addAttribute("listaStudenti", studentRepository.findAll());
        return "studenti";
    }

    @GetMapping("/studenti/nou")
    public String showAddStudentForm(Model model) {
        Student studentNou = new Student();
        model.addAttribute("student", studentNou);
        return "adaugare-student";
    }

    @PostMapping("/studenti/salveaza")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "adaugare-student";
        }
        studentRepository.save(student);
        return "redirect:/studenti";
    }

    @GetMapping("/studenti/editeaza/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "adaugare-student";
    }

    @GetMapping("/studenti/sterge/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
        return "redirect:/studenti";
    }
}