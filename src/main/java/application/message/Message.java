package application.message;


import application.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Size(min = 1, max = 64)
    private String text;
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