package edu.fflora.demo.dao;

import edu.fflora.demo.models.Chantier;
import edu.fflora.demo.models.Consommable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsommableDao extends JpaRepository<Consommable, Integer> {

    Optional<Consommable> findByNom(String nomRecherche);
}
