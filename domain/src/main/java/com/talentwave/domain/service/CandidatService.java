package com.talentwave.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.nio.charset.StandardCharsets;

import com.talentwave.domain.model.Candidat;
import com.talentwave.domain.model.ExperienceProfessionnelle;
import com.talentwave.domain.model.Formation;
import com.talentwave.domain.repository.CandidatRepository;


@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    public void saveCandidat(Candidat candidat) {
        candidatRepository.save(candidat);
    }

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public List<Candidat> searchCandidats(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllCandidats();
        }
        return candidatRepository.searchCandidats(searchTerm.trim());
    }

    public void deleteCandidat(UUID id) {
        candidatRepository.deleteById(id);
    }

    public Candidat getCandidatById(UUID id) {
        return candidatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidat not found with id: " + id));
    }

    public void updateCandidat(Candidat candidat) {
        
        candidatRepository.save(candidat);
    }
}