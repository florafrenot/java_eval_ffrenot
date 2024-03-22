package edu.fflora.demo.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fflora.demo.views.CategoryView;
import edu.fflora.demo.views.RoleView;
import edu.fflora.demo.views.UserView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(RoleView.class)
    protected Integer id;

    @Column(unique = true, length = 30, nullable = false)
    @Length(min = 3, max = 30, message = "Le nom du rôle doit avoir entre 3 & 30 caractères.")
    @NotNull(message = "Le nom du rôle est obligatoire.")
    @JsonView({RoleView.class, UserView.class})
    protected String designation;

    @OneToMany(mappedBy = "role")
    @JsonView(CategoryView.class)
    protected List<User> userList = new ArrayList<>();
}
