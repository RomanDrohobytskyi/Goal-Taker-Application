package application.test;

import application.entities.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "aim_test")
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class AimTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    //@NotNull
    private String title;
    @Column
    //@NotNull
    private String description;
    @Column(columnDefinition = "varchar(255) default ''")
    //@NotNull
    private String text;
    @ManyToOne
    private User user;
    /*@OneToMany(mappedBy = "aim", cascade=CascadeType.ALL, targetEntity=Time.class, fetch=FetchType.EAGER)
    @Column(name = "logged_time")
    private Set<Time> loggedTime;*/
}
