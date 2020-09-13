package application.enums;

public class State {

    public enum Message {
        NEW ("New note"),
        DELETED ("Note has been deleted"),
        ACHIEVED ("Note has been achieved"),
        EDITED ("Note has been edited");

        private String state;

        Message(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }

    public enum Aim {
        NEW ("New aim"),
        ARCHIVED ("Aim has been archived"),
        DELETED ("Aim has been deleted"),
        ACHIEVED ("Aim has been achieved"),
        EDITED ("Aim has been edited");

        private String state;

        Aim(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }

    public enum Date {
        NEW ("New date"),
        DELETED ("Date has been deleted"),
        EDITED ("Date has been edited");

        private String state;

        Date(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }
}
