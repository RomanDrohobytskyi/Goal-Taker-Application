package application.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity (name = "aim")
@Getter
@Setter
@NoArgsConstructor
public class Aim {
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
    @Column(columnDefinition = "datetime default ''") //GETDATE()
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
}
