/**
 * Controller pentru rapoarte și interogări complexe (Statistici).
 * @author Popa Galita Matei Constantin 332AB
 * @version 12 Ianuarie 2026
 */
package com.example.proiectbaze.controller;

import com.example.proiectbaze.model.Cursuri; // IMPORT NOU
import com.example.proiectbaze.repository.PrezenteRepository;
import com.example.proiectbaze.repository.CursuriRepository;
import com.example.proiectbaze.repository.ProfesorRepository;
import com.example.proiectbaze.repository.SedinteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StatisticiController {

    @Autowired
    private PrezenteRepository prezenteRepository;

    @Autowired
    private CursuriRepository cursuriRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private SedinteRepository sedinteRepository;

    @GetMapping("/statistici")
    public String showStatisticiPage(
            @RequestParam(name = "minPrezente", required = false, defaultValue = "0") Long minPrezente,
            @RequestParam(name = "cautaProfesor", required = false) String cautaProfesor, // PARAMETRU NOU
            Model model) {

        // 1. Interogarea cu Parametru Variabil (Studenti cu minim X prezente)
        List<Object[]> rezultatePrezente = prezenteRepository.findStudentiCuPrezenteMinime(minPrezente);
        model.addAttribute("listaStatistica", rezultatePrezente);
        model.addAttribute("valoareCurenta", minPrezente);

        // --- NOU: 1b. Interogare Simplă cu JOIN și Parametru Variabil (Caută Cursuri după Profesor) ---
        List<Cursuri> rezultateCautare = null;
        if (cautaProfesor != null && !cautaProfesor.isEmpty()) {
            rezultateCautare = cursuriRepository.findCursuriByNumeProfesor(cautaProfesor);
        }
        model.addAttribute("rezultateCautareProfesor", rezultateCautare);
        model.addAttribute("numeProfesorCautat", cautaProfesor);


        // 2. Interogare Complexă: Top Cursuri Populare
        List<Object[]> rezultateCursuri = cursuriRepository.findCursuriPopulare();
        model.addAttribute("topCursuri", rezultateCursuri);

        // 3. Interogare Complexă: Statistici Profesori (Grad de încărcare)
        List<Object[]> rezultateProfesori = profesorRepository.findStatisticiProfesori();
        model.addAttribute("topProfesori", rezultateProfesori);

        // 4. Interogare Complexă: Tipuri Ședințe (Curs vs Lab)
        List<Object[]> rezultateTip = sedinteRepository.findPrezentePerTip();
        model.addAttribute("statisticiTip", rezultateTip);

        return "statistici";
    }
}