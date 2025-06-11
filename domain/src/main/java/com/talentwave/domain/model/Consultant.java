package com.talentwave.domain.model;

import jakarta.persistence.*;
//import java.util.List;
import java.util.Set;

@Entity
@Table(name = "consultants")
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String photoUrl;
    private String titre;
    private Integer anneesExperience;
    private String localisation;
    private String disponibilite;
    private String typeContrat;

    @ElementCollection
    @CollectionTable(name = "consultant_secteurs", joinColumns = @JoinColumn(name = "consultant_id"))
    @Column(name = "secteur")
    private Set<String> secteursActivite;

    @ElementCollection
    @CollectionTable(name = "consultant_fonctions", joinColumns = @JoinColumn(name = "consultant_id"))
    @Column(name = "fonction")
    private Set<String> fonctions;

    @ElementCollection
    @CollectionTable(name = "consultant_competences", joinColumns = @JoinColumn(name = "consultant_id"))
    @Column(name = "competence")
    private Set<String> competences;

    @ElementCollection
    @CollectionTable(name = "consultant_langues", joinColumns = @JoinColumn(name = "consultant_id"))
    @Column(name = "langue")
    private Set<String> langues;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getAnneesExperience() {
        return anneesExperience;
    }

    public void setAnneesExperience(Integer anneesExperience) {
        this.anneesExperience = anneesExperience;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Set<String> getSecteursActivite() {
        return secteursActivite;
    }

    public void setSecteursActivite(Set<String> secteursActivite) {
        this.secteursActivite = secteursActivite;
    }

    public Set<String> getFonctions() {
        return fonctions;
    }

    public void setFonctions(Set<String> fonctions) {
        this.fonctions = fonctions;
    }

    public Set<String> getCompetences() {
        return competences;
    }

    public void setCompetences(Set<String> competences) {
        this.competences = competences;
    }

    public Set<String> getLangues() {
        return langues;
    }

    public void setLangues(Set<String> langues) {
        this.langues = langues;
    }
} 