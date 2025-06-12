package com.talentwave.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentwave.domain.model.Candidat;


@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    

}
