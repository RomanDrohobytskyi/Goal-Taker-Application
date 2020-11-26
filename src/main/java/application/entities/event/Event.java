package application.entities.event;

import application.entities.user.User;
import application.models.DayOfWeek;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @Column(name = "starts_from")
    private LocalTime from;
    @NotNull
    @Column(name = "end_to")
    private LocalTime to;
    @NotNull
    private Date date;
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;
    @Column(name = "creation_date")
    @NotNull
    private Date creationDate;
    @Column(name = "modification_date")
    private Date modificationDate;
    @ManyToOne
    private User user;
}
