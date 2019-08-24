package error;

import lombok.Getter;

public class PhoneValidationException extends Exception {

    public static final int PHONE_INVALID = 5;
    public static final int PHONE_ALREADY_EXIST = 8;
    public static final int PHONEs_COUNT_ERROR = 6;

    @Getter
    private int code;

    public PhoneValidationException(int code){
        this.code = code;
    }

    public static PhoneValidationException getPhonesCountLimitException(){
        return new PhoneValidationException(PHONEs_COUNT_ERROR);
    }

    public static PhoneValidationException getphoneAlreadyExistException(){
        return new PhoneValidationException(PHONE_ALREADY_EXIST);
    }

    public static PhoneValidationException getPhoneInvalidException(){
        return new PhoneValidationException(PHONE_INVALID);
    }
}
