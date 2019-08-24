package error;

import lombok.Getter;


public class EmailValidationException extends Exception {

    @Getter
    private Type type;

    public EmailValidationException(Type type){
        this.type = type;
    }

    public static EmailValidationException getEmailEmpty(){
        return new EmailValidationException(Type.EMAIL_EMPTY);
    }

    public static EmailValidationException getEmailInvalidException(){
        return new EmailValidationException(Type.EMAIL_INVALID);
    }

    public enum Type{
        EMAIL_EMPTY,
        EMAIL_INVALID
    }
}
