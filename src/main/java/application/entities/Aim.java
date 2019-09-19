package application.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull
    @Column(columnDefinition = "varchar(255) default ''")
    private String text;
    @ManyToOne
    private User user;
}
