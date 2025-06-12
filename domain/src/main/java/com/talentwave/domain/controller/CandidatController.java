package com.talentwave.domain.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.talentwave.domain.model.Candidat;

import com.talentwave.domain.service.CandidatService;


@Controller
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @GetMapping("/candidat/add")
    public String addCandidat(Model model) {
        model.addAttribute("candidat", new Candidat());
        return "candidat/add-candidat";
    }


    @PostMapping("/candidat/add")
    public String saveCandidat(@ModelAttribute("candidat") Candidat candidat) {
        candidatService.saveCandidat(candidat);
        return "redirect:/candidat/add-candidat";
    }

    @GetMapping("/candidat/list")
    public String voirListCandidats(Model model) {
        model.addAttribute("candidats", candidatService.getAllCandidats());
        return "candidat/list-candidats";
    }

}
