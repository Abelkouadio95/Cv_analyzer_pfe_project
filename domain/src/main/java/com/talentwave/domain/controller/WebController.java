package com.talentwave.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.talentwave.domain.service.CandidatService;
import jakarta.servlet.http.HttpSession;


@Controller
public class WebController {
    @Autowired
    private CandidatService candidatService;

    @GetMapping("/")
    public String index(Model model) {
        long totalCandidats = candidatService.countCandidats();
        long totalConsultants = candidatService.countConsultants();
        long totalCvAttente = candidatService.countCvAttente();
        model.addAttribute("totalCandidats", totalCandidats);
        model.addAttribute("totalConsultants", totalConsultants);
        model.addAttribute("totalCvAttente", totalCvAttente);
        model.addAttribute("pageTitle", "GESTION DE PROFILS CV");
        return "home"; // retourne le nom du template Thymeleaf (home.html)
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/"; // ou vers la page de connexion si tu en as une
    }
}
