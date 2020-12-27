package application.enums;

public enum UserRegisterValidationState {
    EMPTY_EMAIL("EMPTY_EMAIL", "Sorry, Your email is empty, please check it again"),
    USER_EXIST("USER_EXIST", "Sorry, user with this email already exist!"),
    PASSWORDS_NOT_MATCHING("PASSWORDS_NOT_MATCHING", "Sorry, but Your passwords do not match, check it again!"),
    CODE_SENDING_FAILED("CODE_SENDING_FAILED", "We can`t send to You activation code, sorry!"),
    SUCCESS("SUCCESS", "Success");

    public final String state;
    public final String description;

    UserRegisterValidationState(String state, String description){
        this.state = state;
        this.description = description;
    }
}
