package application.enums;

public class State {

    public enum MessageState{
        NEW ("New note"),
        DELETED ("Note has been deleted"),
        ACHIEVED ("Note has been achieved"),
        EDITED ("Note has been edited");

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
        ACHIEVED ("Aim has been achieved"),
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
