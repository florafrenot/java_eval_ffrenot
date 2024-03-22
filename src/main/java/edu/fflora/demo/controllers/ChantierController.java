package edu.fflora.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.dao.ChantierDao;
import edu.fflora.demo.dao.OperationDao;
import edu.fflora.demo.models.Chantier;
import edu.fflora.demo.models.Operation;
import edu.fflora.demo.views.ChantierView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChantierController {
    @Autowired
    ChantierDao chantierDao;

    @Autowired
    private OperationDao operationDao;

    @GetMapping("/chantier/list")
    @Secured({"ROLE_CHEF", "ROLE_ADMINISTRATEUR"})
    @JsonView(ChantierView.class)
        public List<Chantier> list() {
        List<Chantier> chantierList = chantierDao.findAll();
        return chantierList;
    }

    @GetMapping("/chantier/{id}")
    @Secured({"ROLE_CHEF", "ROLE_ADMINISTRATEUR"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> get(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/chantier/{id}/duree")
    @Secured({"ROLE_CHEF", "ROLE_ADMINISTRATEUR"})
    public ResponseEntity<Integer> calculateTotalTime(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);
        if (!optionalChantier.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<Operation> operations = operationDao.findByChantierId(id);

        int totalTime = 0;
        for (Operation operation : operations) {
            totalTime += operation.getTache().getTemps_realisation();
        }

        return ResponseEntity.ok(totalTime);
    }


    @GetMapping("/chantier-by-nom/{nom}")
    @Secured({"ROLE_CHEF", "ROLE_ADMINISTRATEUR"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> getByDesignation(@PathVariable String nom) {
        Optional<Chantier> optionalChantier = chantierDao.findByNom(nom);

        if (optionalChantier.isPresent()) {
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/chantier/{id}")
    @Secured({"ROLE_CHEF", "ROLE_ADMINISTRATEUR"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> delete(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.deleteById(id);
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/chantier")
    @Secured({"ROLE_CHEF", "ROLE_ADMINISTRATEUR"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> create(@RequestBody @Valid Chantier chantier) {
        chantier.setId(null);
        chantierDao.save(chantier);
        return new ResponseEntity<>(chantier, HttpStatus.CREATED);
    }

    @PutMapping("/chantier/{id}")
    @Secured({"ROLE_CHEF", "ROLE_ADMINISTRATEUR"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> update(@RequestBody @Valid Chantier chantier, @PathVariable int id) {
        chantier.setId(id);

        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if(optionalChantier.isPresent()){
            chantierDao.save(chantier);
            return new ResponseEntity<>(chantier, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
