package com.talentwave.domain.controller;

//import com.talentwave.domain.model.Consultant;
import com.talentwave.domain.service.ConsultantService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.talentwave.domain.model.Candidat;
import com.talentwave.domain.model.ExperienceProfessionnelle;
import com.talentwave.domain.model.Formation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

//import java.util.List;

@Controller
public class ConsultantController {
    Client geminiClient = Client.builder().apiKey("AIzaSyCG3OFy6MNlGErm8_nN1tZUVW9nF4XYj7Q").build();

    @Autowired
    private ConsultantService consultantService;


    @GetMapping("/consultant/create/cv")
    public String createConsultantByCV(Model model) {
        model.addAttribute("pageTitle", "Créer un consultant par CV");
        model.addAttribute("username", "Admin");
        return "consultant/create-cv";
    }

    @PostMapping("/consultant/saisir")
    public ResponseEntity<?> saveConsultant(@RequestBody ConsultantPayload payload) {
        consultantService.saveConsultantWithDetails(payload.candidat, payload.experiences, payload.formations);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/consultant/create/saisir")
    public String createConsultantBySaisir(Model model) {
        model.addAttribute("consultant", new Candidat());
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

    // DTO for JSON payload
    public static class ConsultantPayload {
        public Candidat candidat;
        public List<ExperienceProfessionnelle> experiences;
        public List<Formation> formations;
    }

    private String cleanGeminiJson(String raw) {
        if (raw == null) return "";
        // Remove triple backticks and optional 'json' after the first ```
        String cleaned = raw.trim();
        if (cleaned.startsWith("```")) {
            cleaned = cleaned.replaceFirst("^```json\\s*", ""); // Remove ```json (with optional whitespace)
            cleaned = cleaned.replaceFirst("^```\\s*", "");     // Or just ```
        }
        if (cleaned.endsWith("```")) {
            cleaned = cleaned.substring(0, cleaned.lastIndexOf("```")).trim();
        }
        return cleaned;
    }

    @PostMapping("/consultant/create/cv/extract")
    public ResponseEntity<?> extractFromCV(@RequestParam("cvFile") MultipartFile file) {
        try {
            // 1. Convert PDF to text
            String cvText = pdfToText(file.getInputStream());

            // 2. Build the Gemini prompt
            String prompt = buildGeminiPrompt(cvText);

            // 3. Call Gemini API (implement this method)
            String geminiResponse = cleanGeminiJson(callGemini(prompt));

            // 4. Parse Gemini's JSON response and create entities
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(geminiResponse);

            Candidat candidat = new Candidat();
            candidat.setNom(root.path("nom").asText(""));
            candidat.setPrenom(root.path("prenom").asText(""));
            candidat.setEmail(root.path("email").asText(""));
            candidat.setTelephone(root.path("telephone").asText(""));
            candidat.setAdresse(root.path("adresse").asText(""));
            candidat.setSecteur(root.path("secteur").asText(""));
            candidat.setFonction(root.path("fonction").asText(""));
            candidat.setNiveauExperience(root.path("niveauExperience").asText(""));
            candidat.setCompetencesList(mapper.convertValue(root.path("competences"), List.class));
            candidat.setLanguesList(mapper.convertValue(root.path("langues"), List.class));
            candidat.setQualitesList(mapper.convertValue(root.path("qualites"), List.class));
            candidat.setCentresInteretList(mapper.convertValue(root.path("centresInteret"), List.class));
            candidat.setIsConsultant(true);

            List<ExperienceProfessionnelle> experiences = new ArrayList<>();
            for (JsonNode expNode : root.path("experiences")) {
                ExperienceProfessionnelle exp = new ExperienceProfessionnelle();
                exp.setPoste(expNode.path("poste").asText(""));
                exp.setEntreprise(expNode.path("entreprise").asText(""));
                exp.setDateDebut(expNode.path("dateDebut").asText(""));
                exp.setDateFin(expNode.path("dateFin").asText(""));
                exp.setPosteActuel(expNode.path("posteActuel").asBoolean(false));
                exp.setDescription(expNode.path("description").asText(""));
                experiences.add(exp);
            }

            List<Formation> formations = new ArrayList<>();
            for (JsonNode formNode : root.path("formations")) {
                Formation form = new Formation();
                form.setDiplome(formNode.path("diplome").asText(""));
                form.setEtablissement(formNode.path("etablissement").asText(""));
                form.setDateObtention(formNode.path("dateObtention").asText(""));
                formations.add(form);
            }

            consultantService.saveConsultantWithDetails(candidat, experiences, formations);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to process CV: " + e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    private String pdfToText(InputStream pdfStream) throws Exception {
        try (PDDocument document = PDDocument.load(pdfStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private String buildGeminiPrompt(String cvText) {
        return "You are an expert at extracting structured data from CVs (resumes) in both French and English. " +
                "Given the following CV (resume) text, extract the following information and return it as a single JSON object with these fields:\n" +
                "{\n" +
                "  \"nom\": \"\",\n" +
                "  \"prenom\": \"\",\n" +
                "  \"email\": \"\",\n" +
                "  \"telephone\": \"\",\n" +
                "  \"adresse\": \"\",\n" +
                "  \"secteur\": \"\",\n" +
                "  \"fonction\": \"\",\n" +
                "  \"niveauExperience\": \"\",\n" +
                "  \"competences\": [\"\", \"\", ...],\n" +
                "  \"langues\": [\"\", \"\", ...],\n" +
                "  \"qualites\": [\"\", \"\", ...],\n" +
                "  \"centresInteret\": [\"\", \"\", ...],\n" +
                "  \"experiences\": [\n" +
                "    {\"poste\": \"\", \"entreprise\": \"\", \"dateDebut\": \"\", \"dateFin\": \"\", \"posteActuel\": false, \"description\": \"\"}\n" +
                "  ],\n" +
                "  \"formations\": [\n" +
                "    {\"diplome\": \"\", \"etablissement\": \"\", \"dateObtention\": \"\"}\n" +
                "  ]\n" +
                "}\n" +
                "- If a field is not present, leave it as an empty string or empty array.\n" +
                "- Dates should be in YYYY-MM-DD format if possible, otherwise as found.\n" +
                "- For experiences, try to extract all jobs, internships, or relevant professional experiences.\n" +
                "- For formations, extract all degrees, diplomas, or relevant education.\n" +
                "- For competences, langues, qualites, and centresInteret, extract as many as possible.\n" +
                "- The JSON must be valid and easy to parse.\n" +
                "Here is the CV text:\n---\n" + cvText + "\n---";
    }

    private String callGemini(String prompt) {
       try {
         GenerateContentResponse response = geminiClient.models.generateContent("gemini-1.5-flash", prompt, null);
         return response.text();
       }catch(Exception e){
        e.printStackTrace();
        return "Error: " + e.getMessage();
       }
    }
}