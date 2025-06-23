package com.talentwave.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "experience_professionnelle")
public class ExperienceProfessionnelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String poste;
    @Lob
    private String entreprise;
    private String dateDebut;
    private String dateFin;
    private boolean posteActuel;
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
    public String getEntreprise() { return entreprise; }
    public void setEntreprise(String entreprise) { this.entreprise = entreprise; }
    public String getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }
    public String getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }
    public boolean isPosteActuel() { return posteActuel; }
    public void setPosteActuel(boolean posteActuel) { this.posteActuel = posteActuel; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Candidat getCandidat() { return candidat; }
    public void setCandidat(Candidat candidat) { this.candidat = candidat; }
} 