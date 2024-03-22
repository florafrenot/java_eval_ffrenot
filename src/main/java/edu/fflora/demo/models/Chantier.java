package edu.fflora.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.views.ChantierView;
import edu.fflora.demo.views.OperationView;
import edu.fflora.demo.views.UserView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity

public class Chantier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ChantierView.class)
    protected Integer id;

    @Column(nullable = false, unique = true, length = 50)
    @Length(min = 3, max = 50, message = "Le nom doit comporter entre 10 & 50 caractères.") // validation de données
    @NotNull(message = "Le nom du chantier est obligatoire.")
    @JsonView({ChantierView.class, OperationView.class})
    protected String nom;

    @Column(nullable = false, unique = true)
    @NotNull(message = "L'adresse du chantier est obligatoire.")
    @JsonView({ChantierView.class, OperationView.class})
    protected String adresse;

    @ManyToOne
    @JsonView(UserView.class)
    @JoinColumn(name = "client_id")
    protected User client;

    @ManyToOne
    @JsonView(UserView.class)
    @JoinColumn(name = "ouvrier_id")
    protected User ouvrier;
}
