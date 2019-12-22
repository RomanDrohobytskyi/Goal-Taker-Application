package application.entities.aim;

import application.entities.time.data.Time;
import application.entities.user.User;
import application.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity(name = "aim")
@Getter
@Setter
@NoArgsConstructor
public class Aim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    @Size(min = 3, max = 32)
    private String title;
    @Column
    @NotNull
    private String description;
    @Column(columnDefinition = "varchar(255) default ''")
    @NotNull
    private String text;
    @Column
    @NotNull
    private String aimState = State.AimState.NEW.toString();
    @Column(columnDefinition = "datetime default GETDATE()") //GETDATE()
    @NotNull
    private Date creationDate;
    @Column
    private Date modificationDate;
    @Column
    private Date deletionDate;
    @Column
    private Date dateFrom;
    @Column
    private Date dateTo;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "aim", targetEntity=Time.class, fetch=FetchType.EAGER)
    @Column(name = "logged_time")
    private List<Time> loggedTime;

}
