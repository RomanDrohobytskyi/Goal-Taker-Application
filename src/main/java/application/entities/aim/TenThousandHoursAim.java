package application.entities.aim;

import application.entities.time.data.TenThousandHoursAimTime;
import application.entities.user.User;
import application.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity(name = "ten_thousand_hours_aim")
@Getter
@Setter
@NoArgsConstructor
public class TenThousandHoursAim {
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
    @NotNull
    @CreationTimestamp
    private Date creationDate;
    @Column
    private Date modificationDate;
    @Column
    private Date deletionDate;
    @Column(name = "achieved_date")
    private Date achievedDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "aim", targetEntity = TenThousandHoursAimTime.class, fetch = FetchType.EAGER)
    @Column(name = "logged_time")
    private Set<TenThousandHoursAimTime> loggedTime;

}