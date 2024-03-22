package edu.fflora.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.views.OperationView;
import edu.fflora.demo.views.TacheView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.annotation.Secured;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity

public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false)
    @JsonView({TacheView.class, OperationView.class})
    protected String nom;

    @Column(nullable = false)
    protected int temps_realisation;

    @ManyToMany
    @JoinTable(
            name = "tache_consommable",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "consommable_id")
    )
    @JsonView(TacheView.class)
    protected List<Consommable> tagList = new ArrayList<>();}
