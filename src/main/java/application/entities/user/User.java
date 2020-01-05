package application.entities.user;

import application.entities.aim.Aim;
import application.entities.aim.TenThousandHoursAim;
import application.entities.message.Message;
import application.roles.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity (name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails{
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
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Message> message;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Aim> aims;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<TenThousandHoursAim> tenThousandHoursAims;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    /*TODO: methods to do */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
