package com.talentwave.domain.model;


import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


@Entity
@Table(name = "candidats")
public class Candidat {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private java.util.UUID id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String cvPath;
    private String secteur;
    private String fonction;
    private String niveauExperience;
    @Column(name = "is_consultant", columnDefinition = "boolean default false")
    private boolean isConsultant;

    // Multi-value fields as comma-separated strings
    @Lob
    private String competences; // e.g. "Java,Spring,SQL"
    @Lob
    private String langues; // e.g. "Français,Anglais"
    @Lob
    private String qualites; // e.g. "Rigoureux,Créatif"
    @Lob
    private String centresInteret; // e.g. "Lecture,Musique"

    // Helper methods for parsing comma-separated fields
    public List<String> getCompetencesList() {
        return competences == null || competences.isEmpty() ? new ArrayList<>() : Arrays.asList(competences.split(","));
    }
    public void setCompetencesList(List<String> list) {
        this.competences = String.join(",", list);
    }
    public List<String> getLanguesList() {
        return langues == null || langues.isEmpty() ? new ArrayList<>() : Arrays.asList(langues.split(","));
    }
    public void setLanguesList(List<String> list) {
        this.langues = String.join(",", list);
    }
    public List<String> getQualitesList() {
        return qualites == null || qualites.isEmpty() ? new ArrayList<>() : Arrays.asList(qualites.split(","));
    }
    public void setQualitesList(List<String> list) {
        this.qualites = String.join(",", list);
    }
    public List<String> getCentresInteretList() {
        return centresInteret == null || centresInteret.isEmpty() ? new ArrayList<>() : Arrays.asList(centresInteret.split(","));
    }
    public void setCentresInteretList(List<String> list) {
        this.centresInteret = String.join(",", list);
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getNiveauExperience() {
        return niveauExperience;
    }

    public void setNiveauExperience(String niveauExperience) {
        this.niveauExperience = niveauExperience;
    }

    // Getters et Setters
    public java.util.UUID getId() {
        return id;
    }

    public void setId(java.util.UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public boolean isConsultant() {
        return isConsultant;
    }

    public void setIsConsultant(boolean isConsultant) {
        this.isConsultant = isConsultant;
    }

    @OneToMany(mappedBy = "candidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExperienceProfessionnelle> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "candidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Formation> formations = new ArrayList<>();

    public List<ExperienceProfessionnelle> getExperiences() {
        return experiences;
    }
    public List<Formation> getFormations() {
        return formations;
    }

    public String getCompetences() {
        return competences;
    }
    public String getLangues() {
        return langues;
    }
    public String getQualites() {
        return qualites;
    }
    public String getCentresInteret() {
        return centresInteret;
    }
} 

