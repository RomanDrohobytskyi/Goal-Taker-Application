package application.entities.aim;

import application.entities.time.data.Time;
import application.entities.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity(name = "aim_history")
@Getter
@Setter
@NoArgsConstructor
public class AimHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 3, max = 32)
    private String title;
    @NotNull
    private String description;
    @Column(columnDefinition = "varchar(255) default ''")
    @NotNull
    private String text;
    @CreationTimestamp
    @Column() //columnDefinition = "datetime default ''"
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
    @OneToMany(targetEntity=Time.class, fetch=FetchType.EAGER)
    @Column(name = "logged_time")
    private List<Time> loggedTime;

}
