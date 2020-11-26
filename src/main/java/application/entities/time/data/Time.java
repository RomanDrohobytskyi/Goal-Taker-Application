package application.entities.time.data;

import application.entities.aim.Aim;
import application.enums.State;
import application.models.ConvertedDate;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "smart_aim_time")
@Getter
@Setter
@Builder
@AllArgsConstructor
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
    private java.util.Date date;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "creation_date")
    private java.util.Date creationDate;
    @Column(name = "modification_date")
    private java.util.Date modificationDate;
    @Column(name = "state")
    private String state = State.Date.NEW.toString();
    @ManyToOne
    private Aim aim;

    public ConvertedDate getConvertedDate(Time time){
        return new ConvertedDate(time.getDate());
    }
}
