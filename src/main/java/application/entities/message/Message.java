package application.entities.message;


import application.entities.user.User;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 350)
    private String text;
    @NotNull
    @Size(max = 20)
    private String tag;
    private String filename;
    @NotNull
    private String messageState = State.MessageState.NEW.toString();
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