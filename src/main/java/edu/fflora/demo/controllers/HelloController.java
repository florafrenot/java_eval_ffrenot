package edu.fflora.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "<h1>Le serveur fonctionne mais aucune données les gars!</h1>";
    }
}

