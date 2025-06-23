package com.talentwave.domain.repository;

import com.talentwave.domain.model.ExperienceProfessionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceProfessionnelleRepository extends JpaRepository<ExperienceProfessionnelle, Long> {
} 