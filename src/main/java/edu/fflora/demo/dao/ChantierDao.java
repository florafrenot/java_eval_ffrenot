package edu.fflora.demo.dao;

import edu.fflora.demo.models.Chantier;
import edu.fflora.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChantierDao extends JpaRepository<Chantier, Integer> {

    Optional<Chantier> findByNom(String nomRecherche);
}
