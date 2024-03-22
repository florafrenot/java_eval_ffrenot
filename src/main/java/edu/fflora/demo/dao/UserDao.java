package edu.fflora.demo.dao;

import edu.fflora.demo.models.Operation;
import edu.fflora.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByPseudo(String pseudo);

}
