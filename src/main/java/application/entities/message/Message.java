package application.entities.message;


import application.entities.user.User;
import application.enums.State;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity (name = "message")
@Getter
@Setter
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
    private String state = State.Message.NEW.toString();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public String getAuthorName(){
        return user != null ? user.getUsername() : "<none>";
    }

    public String getAuthorEmail(){
        return user != null ? user.getEmail() : "<none>";
    }

    private Message(){}

    private Message(MessageBuilder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.tag = builder.tag;
        this.filename = builder.filename;
        this.state = builder.state;
        this.user = builder.user;
    }

    public static class MessageBuilder {
        private Long id;
        private String text;
        private String tag;
        private String filename;
        private String state;
        private User user;


        public MessageBuilder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public MessageBuilder state(String state) {
            this.state = state;
            return this;
        }

        public MessageBuilder user(User user) {
            this.user = user;
            return this;
        }

        public MessageBuilder(String text, String tag, User user) {
            this.text = text;
            this.tag = tag;
            this.user = user;
        }

        public Message build(){
            return new Message(this);
        }
    }
/*
        public AimBuilder(String title, String description, String text) {
            this.title = title;
            this.description = description;
            this.text = text;
        }

        public Aim build(){
            return new Aim(this);
        }
    }*/
}