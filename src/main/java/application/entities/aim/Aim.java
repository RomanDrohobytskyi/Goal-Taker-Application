package application.entities.aim;

import application.entities.time.data.Time;
import application.entities.user.User;
import application.enums.State;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity(name = "aim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Aim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotBlank
    private String title;
    @Column
    @NotBlank
    private String description;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String text;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String specify;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String measurable;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String attainable;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String relevant;
    @Column
    private Date timeBased;
    @Column
    @NotNull
    private String aimState = State.Aim.NEW.toString();
    @Column
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
    @Column(name = "achieved_date")
    private Date achievedDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "aim", cascade=CascadeType.ALL, targetEntity=Time.class, fetch=FetchType.EAGER)
    @Column(name = "logged_time")
    private Set<Time> loggedTime;

    @Override
    public String toString() {
        return "Aim{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
