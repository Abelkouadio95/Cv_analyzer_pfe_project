package com.talentwave.domain.controller;

//import com.talentwave.domain.model.Consultant;
import com.talentwave.domain.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import com.talentwave.domain.model.Consultant;

//import java.util.List;

@Controller
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;


    @GetMapping("/consultant/create/cv")
    public String createConsultantByCV(Model model) {
        model.addAttribute("pageTitle", "Créer un consultant par CV");
        model.addAttribute("username", "Admin");
        return "consultant/create-cv";
    }

    @PostMapping("/consultant/saisir")
    public String saveConsultant(@ModelAttribute("consultant") Consultant consultant) {
        consultantService.saveConsultant(consultant);
        return "redirect:/";
        
    }

    @GetMapping("/consultant/create/saisir")
    public String createConsultantBySaisir(Model model) {
        model.addAttribute("consultant", new Consultant());
        return "consultant/create-saisir";
    }

    @GetMapping("/consultant/create/candidat")
    public String createConsultantByCandidat(Model model) {
        model.addAttribute("pageTitle", "Créer un consultant à partir d'un candidat");
        model.addAttribute("username", "Admin");
        return "consultant/create-candidat";
    }

    @GetMapping("/consultant/search")
   /* public String searchProfiles(
            @RequestParam(required = false) List<String> secteurs,
            @RequestParam(required = false) List<String> fonctions,
            @RequestParam(required = false) List<String> competences,
            @RequestParam(required = false) List<String> langues,
            @RequestParam(required = false) String localisation,
            @RequestParam(required = false) String typeContrat,
            @RequestParam(required = false) String disponibilite,
            @RequestParam(required = false) Integer anneesExperience,
            Model model
    ) {
        List<Consultant> consultants = consultantService.searchConsultants(
                secteurs,
                fonctions,
                competences,
                langues,
                localisation,
                typeContrat,
                disponibilite,
                anneesExperience
        ); */
    public String searchProfiles(Model model){
        //model.addAttribute("consultants", consultants);
        return "consultant/search-profiles";
    }

}