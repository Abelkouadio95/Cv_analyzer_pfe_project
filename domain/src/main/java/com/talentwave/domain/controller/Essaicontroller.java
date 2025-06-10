package com.talentwave.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Essaicontroller {

    @GetMapping("/accueil")
    public String accueil(Model model) {
        model.addAttribute("nom", "Jean");
        return "accueil"; 
    }
}