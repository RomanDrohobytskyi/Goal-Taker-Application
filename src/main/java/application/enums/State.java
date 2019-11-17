package application.enums;

public class State {

    public enum MessageState{
        NEW ("New message"),
        SENT ("Message has been sent"),
        RECEIVED ("Message has been received"),
        READ ("Message has been read"),
        ARCHIVED ("Message has been archived"),
        DELETED ("Message has been deleted"),
        RECOVERED ("Message has been recovered"),
        EDITED ("Message has been edited");

        private String state;

        MessageState(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }

    }

    public enum AimState{
        NEW ("New aim"),
        ARCHIVED ("Aim has been archived"),
        DELETED ("Aim has been deleted"),
        EDITED ("Aim has been edited");

        private String state;

        AimState(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }

    public enum DateState{
        NEW ("New date"),
        DELETED ("Date has been deleted"),
        EDITED ("Date has been edited");

        private String state;

        DateState(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }
}
