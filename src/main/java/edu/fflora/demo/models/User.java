package edu.fflora.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.views.ChantierView;
import edu.fflora.demo.views.UserView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity // il faut un ird du coup

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false, unique = true, length = 100) // code obligatoire et doit être unique
    @Length(min = 3, max = 50, message = "Le pseudo doit comporter min 4 caractères.") // validation de données
    @NotNull(message = "Le pseudo de l'utilisateur est obligatoire.")
    @JsonView({UserView.class, Operation.class})
    protected  String pseudo;

    @Column(nullable = false)
    @NotNull(message = "Le mot de passe de l'utilisateur est obligatoire.")
    protected String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonView({UserView.class, Operation.class})
    protected Role role;

}
