package application.entities.time.data;

import application.entities.aim.Aim;
import application.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "time")
@Getter
@Setter
@NoArgsConstructor
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "time")
    private Double time;
    @NotNull
    @Column(name = "date")
    private Date date;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "modification_date")
    private Date modificationDate;
    @Column(name = "state")
    private String state = State.DateState.NEW.toString();
    @ManyToOne
    private Aim aim;
}
