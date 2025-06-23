package com.talentwave.domain.service;

import com.talentwave.domain.model.Consultant;
import com.talentwave.domain.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.List;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

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

    public void saveConsultant(Consultant consultant) {
        consultantRepository.save(consultant);
    }
} 
