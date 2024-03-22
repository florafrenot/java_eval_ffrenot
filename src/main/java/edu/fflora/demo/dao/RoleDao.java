package edu.fflora.demo.dao;

import edu.fflora.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//extends = hériter
//repository = dao, création d'une instance
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    Optional<Role> findByDesignation(String nomRecherche);
}
