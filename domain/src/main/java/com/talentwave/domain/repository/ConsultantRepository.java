package com.talentwave.domain.repository;

import com.talentwave.domain.model.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    
    /*@Query("SELECT c FROM Consultant c WHERE " +
           "(:secteurs IS NULL OR c.secteursActivite IN :secteurs) AND " +
           "(:fonctions IS NULL OR c.fonctions IN :fonctions) AND " +
           "(:competences IS NULL OR c.competences IN :competences) AND " +
           "(:langues IS NULL OR c.langues IN :langues) AND " +
           "(:localisation IS NULL OR c.localisation = :localisation) AND " +
           "(:typeContrat IS NULL OR c.typeContrat = :typeContrat) AND " +
           "(:disponibilite IS NULL OR c.disponibilite = :disponibilite) AND " +
           "(:anneesExperience IS NULL OR c.anneesExperience >= :anneesExperience)")
    List<Consultant> searchConsultants(
            @Param("secteurs") List<String> secteurs,
            @Param("fonctions") List<String> fonctions,
            @Param("competences") List<String> competences,
            @Param("langues") List<String> langues,
            @Param("localisation") String localisation,
            @Param("typeContrat") String typeContrat,
            @Param("disponibilite") String disponibilite,
            @Param("anneesExperience") Integer anneesExperience
    );*/
} 
