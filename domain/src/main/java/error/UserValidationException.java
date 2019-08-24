package error;

import lombok.Getter;

public class UserValidationException extends Exception {


    @Getter
    private Type type;

    public UserValidationException(Type type){
        this.type = type;
    }

    public static UserValidationException getFirstNameEmpty() {
        return new UserValidationException(Type.FIRST_NAME_EMPTY);
    }

    public static UserValidationException getSecondNameEmpty(){
        return new UserValidationException(Type.SECOND_NAME_EMPTY);
    }


    enum Type{
        FIRST_NAME_EMPTY,
        SECOND_NAME_EMPTY
    }
}
