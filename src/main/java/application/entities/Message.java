package application.entities;


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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private String filename;

    public String getAuthorName(){
        return user != null ? user.getUsername() : "<none>";
    }

    public String getAuthorEmail(){
        return user != null ? user.getEmail() : "<none>";
    }
}