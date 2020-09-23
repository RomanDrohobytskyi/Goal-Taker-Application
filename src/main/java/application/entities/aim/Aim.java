package application.entities.aim;

import application.entities.time.data.Time;
import application.entities.user.User;
import application.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity(name = "aim")
@Getter
@Setter
@NoArgsConstructor
public class Aim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotBlank
    private String title;
    @Column
    @NotBlank
    private String description;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String text;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String specify;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String measurable;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String attainable;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String relevant;
    @Column
    private Date timeBased;
    @Column
    @NotNull
    private String aimState = State.Aim.NEW.toString();
    @Column
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
    @Column(name = "achieved_date")
    private Date achievedDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "aim", cascade=CascadeType.ALL, targetEntity=Time.class, fetch=FetchType.EAGER)
    @Column(name = "logged_time")
    private Set<Time> loggedTime;

    @Override
    public String toString() {
        return "Aim{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }

    public Aim(AimBuilder builder){
        this.title = builder.title;
        this.description = builder.description;
        this.text = builder.text;

        this.id = builder.id;
        this.specify = builder.specify;
        this.measurable = builder.measurable;
        this.attainable = builder.attainable;
        this.relevant = builder.relevant;
        this.timeBased =builder.timeBased;
        this.aimState = builder.aimState;
        this.creationDate = builder.creationDate;
        this.modificationDate = builder.modificationDate;
        this.deletionDate = builder.deletionDate;
        this.dateFrom = builder.dateFrom;
        this.dateTo = builder.dateTo;
        this.achievedDate = builder.achievedDate;
        this.user = builder.user;
        this.loggedTime = builder.loggedTime;
    }

    public static class AimBuilder {
        private Long id;
        private final String title;
        private final String description;
        private final String text;
        private String specify;
        private String measurable;
        private String attainable;
        private String relevant;
        private Date timeBased;
        private String aimState = State.Aim.NEW.toString();
        private Date creationDate;
        private Date modificationDate;
        private Date deletionDate;
        private Date dateFrom;
        private Date dateTo;
        private Date achievedDate;
        private User user;
        private Set<Time> loggedTime;

        public AimBuilder(String title, String description, String text) {
            this.title = title;
            this.description = description;
            this.text = text;
        }

        public Aim build(){
            return new Aim(this);
        }

        public AimBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public AimBuilder specify(String specify) {
            this.specify = specify;
            return this;
        }

        public AimBuilder measurable(String measurable) {
            this.measurable = measurable;
            return this;
        }

        public AimBuilder attainable(String attainable) {
            this.attainable = attainable;
            return this;
        }

        public AimBuilder relevant(String relevant) {
            this.relevant = relevant;
            return this;
        }

        public AimBuilder timeBased(Date timeBased) {
            this.timeBased = timeBased;
            return this;
        }

        public AimBuilder aimState(String aimState) {
            this.aimState = aimState;
            return this;
        }

        public AimBuilder creationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public AimBuilder modificationDate(Date modificationDate) {
            this.modificationDate = modificationDate;
            return this;
        }

        public AimBuilder deletionDate(Date deletionDate) {
            this.deletionDate = deletionDate;
            return this;
        }

        public AimBuilder dateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public AimBuilder dateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public AimBuilder achievedDate(Date achievedDate) {
            this.achievedDate = achievedDate;
            return this;
        }

        public AimBuilder user(User user) {
            this.user = user;
            return this;
        }

        public AimBuilder loggedTime(Set<Time> loggedTime) {
            this.loggedTime = loggedTime;
            return this;
        }
    }
}
