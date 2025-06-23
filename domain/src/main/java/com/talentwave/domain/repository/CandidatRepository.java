package com.talentwave.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talentwave.domain.model.Candidat;
import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, UUID> {
    
    @Query("SELECT c FROM Candidat c WHERE " +
           "LOWER(c.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.prenom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.telephone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.adresse) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.secteur) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Candidat> searchCandidats(@Param("searchTerm") String searchTerm);
}
