package application.entities;

import application.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "message")
@Getter
@Setter
@NoArgsConstructor
public class MessageHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 350)
    private String text;
    @NotNull
    @Size(min = 1, max = 20)
    private String tag;
    private String filename;
    @NotNull
    private String messageState = State.MessageState.NEW.toString();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
