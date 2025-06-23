package com.talentwave.domain.service;

import com.talentwave.domain.model.Candidat;
import com.talentwave.domain.model.ExperienceProfessionnelle;
import com.talentwave.domain.model.Formation;
import com.talentwave.domain.repository.CandidatRepository;
import com.talentwave.domain.repository.ExperienceProfessionnelleRepository;
import com.talentwave.domain.repository.FormationRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ConsultantService {

    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private ExperienceProfessionnelleRepository experienceRepository;
    @Autowired
    private FormationRepository formationRepository;

    /*public List<Consultant> searchConsultants(
            List<String> secteurs,
            List<String> fonctions,
            List<String> competences,
            List<String> langues,
            String localisation,
            String typeContrat,
            String disponibilite,
            Integer anneesExperience
    ) {
        return consultantRepository.searchConsultants(
                secteurs,
                fonctions,
                competences,
                langues,
                localisation,
                typeContrat,
                disponibilite,
                anneesExperience
        );
    }*/

    public void saveConsultant(Candidat candidat) {
        candidat.setIsConsultant(true);
        candidatRepository.save(candidat);
    }

    public void saveConsultantWithDetails(Candidat candidat, List<ExperienceProfessionnelle> experiences, List<Formation> formations) {
        candidat.setIsConsultant(true);
        candidat =candidatRepository.save(candidat);

        for (ExperienceProfessionnelle exp : experiences) {
            exp.setCandidat(candidat);
            experienceRepository.save(exp);
        }
        for (Formation form : formations) {
            form.setCandidat(candidat);
            formationRepository.save(form);
        }

        candidat = candidatRepository.findById(candidat.getId()).orElse(null);

        String uploadDir = "uploads/cv/";
        UUID uuid = candidat.getId(); // Make sure the UUID is generated before this step
        String filename = "generic-cv-" + uuid + ".pdf";
        Path filePath = Paths.get(uploadDir, filename);
        try {
            Files.createDirectories(filePath.getParent());
            String content = generateGenericCvContent(candidat, candidat.getExperiences(), candidat.getFormations());
            // Create PDF
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                float y = page.getMediaBox().getHeight() - 50;
                float leading = 16;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, y);
                for (String line : content.split("\n")) {
                    if (y < 50) {
                        contentStream.endText();
                        contentStream.close();
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        contentStream.setFont(PDType1Font.HELVETICA, 12);
                        y = page.getMediaBox().getHeight() - 50;
                        contentStream.beginText();
                        contentStream.newLineAtOffset(50, y);
                    }
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -leading);
                    y -= leading;
                }
                contentStream.endText();
                contentStream.close();
                document.save(filePath.toFile());
            }
            candidat.setCvPath("/" + uploadDir + filename);
            candidatRepository.save(candidat);
        } catch (Exception e) {
            System.out.println("Error writing PDF file: " + e.getMessage());
        }
    }

    private String generateGenericCvContent(Candidat candidat, List<ExperienceProfessionnelle> experiences, List<Formation> formations) {
        StringBuilder sb = new StringBuilder();
        sb.append("Secteur: ").append(candidat.getSecteur()).append("\n");
        sb.append("Fonction: ").append(candidat.getFonction()).append("\n");
        sb.append("Niveau d'expérience: ").append(candidat.getNiveauExperience()).append("\n");
        sb.append("Compétences: ").append(candidat.getCompetences()).append("\n");
        sb.append("Langues: ").append(candidat.getLangues()).append("\n");
        sb.append("Qualités: ").append(candidat.getQualites()).append("\n");
        sb.append("Centres d'intérêt: ").append(candidat.getCentresInteret()).append("\n\n");

        sb.append("Expériences professionnelles:\n");
        for (ExperienceProfessionnelle exp : experiences) {
            sb.append("- Poste: ").append(exp.getPoste()).append(", Entreprise: ").append(exp.getEntreprise())
              .append(", De: ").append(exp.getDateDebut()).append(" à ").append(exp.getDateFin())
              .append(", Description: ").append(exp.getDescription()).append("\n");
        }

        sb.append("\nFormations:\n");
        for (Formation form : formations) {
            sb.append("- Diplôme: ").append(form.getDiplome()).append(", Établissement: ").append(form.getEtablissement())
              .append(", Date d'obtention: ").append(form.getDateObtention()).append("\n");
        }
        return sb.toString();
    }
} 
