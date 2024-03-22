package edu.fflora.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.views.ChantierView;
import edu.fflora.demo.views.ConsommableView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity

public class Consommable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false, unique = true, length = 100)
    @Length(min = 3, max = 50, message = "Le nom du consommable doit comporter min 3 caractères.") // validation de données
    @NotNull(message = "Le nom est obligatoire.")
    @JsonView(ConsommableView.class)
    protected String nom;
}
