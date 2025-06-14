package com.talentwave.domain.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.talentwave.domain.model.Candidat;
import com.talentwave.domain.service.CandidatService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    private final String UPLOAD_DIR = "uploads/cv/";

    @GetMapping("/candidat/add")
    public String addCandidat(Model model) {
        model.addAttribute("candidat", new Candidat());
        return "candidat/add-candidat";
    }

    @PostMapping("/candidat/add")
    public String saveCandidat(@ModelAttribute("candidat") Candidat candidat, 
                             @RequestParam("cvFile") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Create upload directory if it doesn't exist
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Generate unique filename
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFilename = UUID.randomUUID().toString() + extension;

                // Save file
                Path filePath = uploadPath.resolve(newFilename);
                Files.copy(file.getInputStream(), filePath);

                // Set the CV path in the candidat object
                candidat.setCvPath("/" + UPLOAD_DIR + newFilename);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error appropriately
            }
        }

        candidatService.saveCandidat(candidat);
        return "redirect:/candidat/list";
    }

    @GetMapping("/candidat/list")
    public String voirListCandidats(Model model) {
        model.addAttribute("candidats", candidatService.getAllCandidats());
        return "candidat/list-candidats";
    }

    @GetMapping("/candidat/delete/{id}")
    public String deleteCandidat(@PathVariable Long id) {
        candidatService.deleteCandidat(id);
        return "redirect:/candidat/list";
    }

    @GetMapping("/candidat/edit/{id}")
    public String editCandidat(@PathVariable Long id, Model model) {
        Candidat candidat = candidatService.getCandidatById(id);
        model.addAttribute("candidat", candidat);
        return "candidat/edit-candidat";
    }

    @PostMapping("/candidat/update")
    public String updateCandidat(@ModelAttribute("candidat") Candidat candidat,
                               @RequestParam(value = "cvFile", required = false) MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // Create upload directory if it doesn't exist
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Generate unique filename
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFilename = UUID.randomUUID().toString() + extension;

                // Save file
                Path filePath = uploadPath.resolve(newFilename);
                Files.copy(file.getInputStream(), filePath);

                // Set the CV path in the candidat object
                candidat.setCvPath("/" + UPLOAD_DIR + newFilename);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error appropriately
            }
        }

        candidatService.updateCandidat(candidat);
        return "redirect:/candidat/list";
    }
}
