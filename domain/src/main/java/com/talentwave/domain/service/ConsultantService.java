package com.talentwave.domain.service;

import com.talentwave.domain.model.Candidat;
import com.talentwave.domain.model.ExperienceProfessionnelle;
import com.talentwave.domain.model.Formation;
import com.talentwave.domain.repository.CandidatRepository;
import com.talentwave.domain.repository.ExperienceProfessionnelleRepository;
import com.talentwave.domain.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        candidatRepository.save(candidat);

        for (ExperienceProfessionnelle exp : experiences) {
            exp.setCandidat(candidat);
            experienceRepository.save(exp);
        }
        for (Formation form : formations) {
            form.setCandidat(candidat);
            formationRepository.save(form);
        }
    }
} 
