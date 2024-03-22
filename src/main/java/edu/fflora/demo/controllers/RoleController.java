package edu.fflora.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.dao.RoleDao;
import edu.fflora.demo.models.Role;
import edu.fflora.demo.views.RoleView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    // propriété, ajout d'une dépendance, categoryDao est une dépendance de CategoryController
    @Autowired
    RoleDao roleDao;


    @GetMapping("/role/list")
    @Secured({"ROLE_ADMIN"})
    @JsonView(RoleView.class)
    public List<Role> list() {
        List<Role> roleList = roleDao.findAll();
        return  roleList;
    }

    @GetMapping("/role/{id}")
    @Secured({"ROLE_ADMIN"})
    @JsonView(RoleView.class) // elle sera vide là, faut que je l'ajoute dans mes modèles
    public ResponseEntity<Role> get(@PathVariable int id) {

        Optional<Role> optionalRole = roleDao.findById(id);

        if(optionalRole.isPresent()) {
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category-by-nom/{nom}")
    @Secured({"ROLE_ADMIN"})
    @JsonView(RoleView.class)
    public ResponseEntity<Role> getByDesignation(@PathVariable String designation) {

        Optional<Role> optionalRole = roleDao.findByDesignation(designation);

        if(optionalRole.isPresent()) {
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/role/{id}")
    @Secured({"ROLE_ADMIN"})
    @JsonView(RoleView.class)
    public ResponseEntity<Role> delete(@PathVariable int id) {
        Optional<Role> optionalRole = roleDao.findById(id);

        if (optionalRole.isPresent()) {
            roleDao.deleteById(id);
            return new ResponseEntity<>(optionalRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/role")
    @Secured({"ROLE_ADMIN"})
    @JsonView(RoleView.class)
    public ResponseEntity<Role> create(@RequestBody @Valid Role role) {
        role.setId(null);
        roleDao.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PutMapping("/role/{id}")
    @Secured({"ROLE_ADMIN"})
    @JsonView(RoleView.class)
    public ResponseEntity<Role> update(@RequestBody @Valid Role role, @PathVariable int id) {
        role.setId(id);

        Optional<Role> optionalRole = roleDao.findById(id);

        if(optionalRole.isPresent()){
            roleDao.save(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
