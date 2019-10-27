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
}
