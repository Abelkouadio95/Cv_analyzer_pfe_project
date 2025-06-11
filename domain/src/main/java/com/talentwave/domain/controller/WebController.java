package com.talentwave.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "GESTION DE PROFILS CV");
        model.addAttribute("username", "Admin");
        return "index"; // retourne le nom du template Thymeleaf (index.html)
    }

    @GetMapping("/consultant/create/cv")
    public String createConsultantByCV(Model model) {
        model.addAttribute("pageTitle", "Créer un consultant par CV");
        model.addAttribute("username", "Admin");
        return "consultant/create-cv";
    }

    @GetMapping("/consultant/create/saisir")
    public String createConsultantBySaisir(Model model) {
        model.addAttribute("pageTitle", "Créer un consultant par saisie");
        model.addAttribute("username", "Admin");
        return "consultant/create-saisir";
    }

    @GetMapping("/consultant/create/candidat")
    public String createConsultantByCandidat(Model model) {
        model.addAttribute("pageTitle", "Créer un consultant à partir d'un candidat");
        model.addAttribute("username", "Admin");
        return "consultant/create-candidat";
    }

    @GetMapping("/candidat/add")
    public String addCandidat(Model model) {
        model.addAttribute("pageTitle", "Ajouter un candidat");
        model.addAttribute("username", "Admin");
        return "candidat/add-candidat";
    }
}