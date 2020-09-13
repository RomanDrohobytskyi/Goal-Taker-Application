package application.entities.time.data;

import application.entities.aim.TenThousandHoursAim;
import application.enums.State;
import application.utils.ConvertedDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity(name = "ten_thousand_hours_aim_time")
@Getter
@Setter
@NoArgsConstructor
public class TenThousandHoursAimTime {
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
    private TenThousandHoursAim aim;

    public ConvertedDate getConvertedDate(TenThousandHoursAimTime time){
        ConvertedDate convertedDate = new ConvertedDate();
        java.util.Date date = time.getDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        convertedDate.setDay(localDate.getDayOfMonth());
        convertedDate.setMonth(localDate.getMonthValue());
        convertedDate.setYear((long) localDate.getYear());

        return convertedDate;
    }
}
