package com.talentwave.domain.model;


import jakarta.persistence.*;
//import java.util.List;


@Entity
@Table(name = "candidats")
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    //private String cvPath;

    /*@ElementCollection
    @CollectionTable(name = "consultant_secteurs", joinColumns = @JoinColumn(name = "consultant_id"))
    @Column(name = "secteur")
    private Set<String> secteursActivite;*/

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

   /*  public String getcv() {
        return cvPath;
    }

    public void setcv(String cvPath) {
        this.cvPath = cvPath;
    } 

    public Set<String> getSecteursActivite() {
        return secteursActivite;
    }

    public void setSecteursActivite(Set<String> secteursActivite) {
        this.secteursActivite = secteursActivite;
    } */
} 

