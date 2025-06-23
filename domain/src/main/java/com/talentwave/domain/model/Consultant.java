package com.talentwave.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consultants")
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Informations personnelles
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    
    // Informations professionnelles
    private String secteurActivite;
    private String fonction;
    private String niveauExperience;
    private String disponibilite;
    private String cvPath;
    
    // Relations
   /* @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultantLangue> langues = new ArrayList<>();

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultantExperience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultantFormation> formations = new ArrayList<>();

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultantCI> centresInteret = new ArrayList<>();

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultantSoftSkill> softSkills = new ArrayList<>();

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultantCompetence> competences = new ArrayList<>();

    // Méthodes utilitaires pour gérer les relations
    public void addLangue(ConsultantLangue langue) {
        langues.add(langue);
        langue.setConsultant(this);
    }

    public void removeLangue(ConsultantLangue langue) {
        langues.remove(langue);
        langue.setConsultant(null);
    }

    public void addExperience(ConsultantExperience experience) {
        experiences.add(experience);
        experience.setConsultant(this);
    }

    public void removeExperience(ConsultantExperience experience) {
        experiences.remove(experience);
        experience.setConsultant(null);
    }

    public void addFormation(ConsultantFormation formation) {
        formations.add(formation);
        formation.setConsultant(this);
    }

    public void removeFormation(ConsultantFormation formation) {
        formations.remove(formation);
        formation.setConsultant(null);
    }

    public void addCentreInteret(ConsultantCI centreInteret) {
        centresInteret.add(centreInteret);
        centreInteret.setConsultant(this);
    }

    public void removeCentreInteret(ConsultantCI centreInteret) {
        centresInteret.remove(centreInteret);
        centreInteret.setConsultant(null);
    }

    public void addSoftSkill(ConsultantSoftSkill softSkill) {
        softSkills.add(softSkill);
        softSkill.setConsultant(this);
    }

    public void removeSoftSkill(ConsultantSoftSkill softSkill) {
        softSkills.remove(softSkill);
        softSkill.setConsultant(null);
    }

    public void addCompetence(ConsultantCompetence competence) {
        competences.add(competence);
        competence.setConsultant(this);
    }

    public void removeCompetence(ConsultantCompetence competence) {
        competences.remove(competence);
        competence.setConsultant(null);
    } */
}