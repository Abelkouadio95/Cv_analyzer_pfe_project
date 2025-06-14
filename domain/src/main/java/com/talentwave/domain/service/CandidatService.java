package com.talentwave.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentwave.domain.model.Candidat;
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

    public void deleteCandidat(Long id) {
        candidatRepository.deleteById(id);
    }

    public Candidat getCandidatById(Long id) {
        return candidatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidat not found with id: " + id));
    }

    public void updateCandidat(Candidat candidat) {
        candidatRepository.save(candidat);
    }
}