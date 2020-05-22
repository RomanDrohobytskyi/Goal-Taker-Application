package application.entities.time.data;

import application.entities.aim.Aim;
import application.enums.State;
import application.utils.ConvertedDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity(name = "smart_aim_time")
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

    public ConvertedDate getConvertedDate(Time time){
        ConvertedDate convertedDate = new ConvertedDate();
        Date date = time.getDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        convertedDate.setDay(day);
        convertedDate.setMonth(month);
        convertedDate.setYear((long) year);

        return convertedDate;
    }
}
