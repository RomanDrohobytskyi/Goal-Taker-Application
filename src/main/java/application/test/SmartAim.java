package application.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "smart_aim")
@NoArgsConstructor
@Getter
@Setter
public class SmartAim extends AimTest {
    @Column(columnDefinition = "varchar(255) default ''")
    //@NotNull
    private String specify;
    @Column(columnDefinition = "varchar(255) default ''")
    //@NotNull
    private String measurable;
    @Column(columnDefinition = "varchar(255) default ''")
    //@NotNull
    private String attainable;
    @Column(columnDefinition = "varchar(255) default ''")
    //@NotNull
    private String relevant;
    @Column
    private Date timeBased;
}
