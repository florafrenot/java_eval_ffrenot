package edu.fflora.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.views.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@Entity

public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(OperationView.class)
    protected Integer id;

    @Column(unique = true, length = 150)
    @Length(min = 5, max = 50, message = "Le nom de l'opération doit avoir entre 5 & 150 caractères.")
    @NotNull(message = "Le nom de l'opération est obligatoire.")
    @JsonView({OperationView.class, UserView.class})
    protected String nom;

    @ManyToOne(optional = false)
    @JsonView({OperationView.class, ChantierView.class})
    protected Chantier chantier;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tache_id")
    @JsonView({OperationView.class, TacheView.class})
    protected Tache tache;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ouvrier_id")
    @JsonView({OperationView.class, UserView.class})
    protected User user;

    @Temporal(TemporalType.DATE)
    @JsonView({OperationView.class, UserView.class})
    protected Date date;
}
