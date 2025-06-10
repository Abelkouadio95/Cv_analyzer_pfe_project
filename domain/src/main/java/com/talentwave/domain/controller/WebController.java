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
}