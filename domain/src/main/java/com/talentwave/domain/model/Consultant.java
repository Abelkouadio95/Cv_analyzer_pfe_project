package com.talentwave.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    
    
}