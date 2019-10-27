package application.entities;

import application.roles.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "user_history")
@Getter
@Setter
@NoArgsConstructor
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 1, max = 24)
    private String username;
    @NotNull
    @Size(min = 1, max = 34)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 42)
    private String lastName;
    @NotNull
    private String password;
    private String activationCode;
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<Role> roles;
    @OneToMany
    private Set<Aim> aims;

}
