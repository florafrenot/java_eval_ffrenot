
package edu.fflora.demo.dao;

import edu.fflora.demo.models.Consommable;
import edu.fflora.demo.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationDao extends JpaRepository<Operation, Integer> {

    Optional<Operation> findByNom(String nomRecherche);
    List<Operation> findByChantierId(Integer chantierId);

}
