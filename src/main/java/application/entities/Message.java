package application.entities;


import application.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity (name = "message")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 350)
    private String text;
    @NotNull
    @Size(min = 1, max = 20)
    private String tag;
    private String filename;
    //TODO: to String
    @NotNull
    private State.MessageState messageState = State.MessageState.NEW;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public String getAuthorName(){
        return user != null ? user.getUsername() : "<none>";
    }

    public String getAuthorEmail(){
        return user != null ? user.getEmail() : "<none>";
    }
}