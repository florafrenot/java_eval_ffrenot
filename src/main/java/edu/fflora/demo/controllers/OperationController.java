package edu.fflora.demo.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.dao.OperationDao;
import edu.fflora.demo.dao.RoleDao;
import edu.fflora.demo.dao.UserDao;
import edu.fflora.demo.models.Operation;
import edu.fflora.demo.models.Role;
import edu.fflora.demo.models.User;
import edu.fflora.demo.views.OperationView;
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
public class OperationController {

    @Autowired
    OperationDao operationDao;

    @Autowired
    UserDao userDao;

    @GetMapping("/operation/list")
    @Secured({"ROLE_CHEF", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public List<Operation> list() {
        List<Operation> operationList = operationDao.findAll();
        return  operationList;
    }

    @GetMapping("/operation/{id}")
    @Secured({"ROLE_CHEF", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> get(@PathVariable int id) {

        Optional<Operation> optionalOperation = operationDao.findById(id);

        if(optionalOperation.isPresent()) {
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/operation-by-nom/{nom}")
    @Secured({"ROLE_CHEF", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> getByDesignation(@PathVariable String nom) {

        Optional<Operation> optionalOperation = operationDao.findByNom(nom);

        if(optionalOperation.isPresent()) {
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/user/operations")
//    @JsonView(OperationView.class)
//    public ResponseEntity<List<Operation>> getEmployeeOperations(@RequestParam String pseudo) {
//        Optional<User> optionalUser = userDao.findByPseudo(pseudo);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            String role = user.getRole();
//
//            List<Operation> operations;
//            if ("Client".equals(role) || "Ouvrier".equals(role)) {
//                operations = operationDao.findOperationsForUser(user);
//                return new ResponseEntity<>(operations, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


    @DeleteMapping("/operation/{id}")
    @Secured("ROLE_CHEF")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> delete(@PathVariable int id) {
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if (optionalOperation.isPresent()) {
            operationDao.deleteById(id);
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/operation")
    @Secured("ROLE_CHEF")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> create(@RequestBody @Valid Operation operation) {
        operation.setId(null);
        operationDao.save(operation);
        return new ResponseEntity<>(operation, HttpStatus.CREATED);
    }

    @PutMapping("/operation/{id}")
    @Secured("ROLE_CHEF")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> update(@RequestBody @Valid Operation operation, @PathVariable int id) {
        operation.setId(id);

        Optional<Operation> optionalOperation = operationDao.findById(id);

        if(optionalOperation.isPresent()){
            operationDao.save(operation);
            return new ResponseEntity<>(operation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
