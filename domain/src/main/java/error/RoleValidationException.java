package error;

import lombok.Getter;

public class RoleValidationException extends Exception {

    @Getter
    private Type type;

    public RoleValidationException(Type type){
        this.type = type;
    }

    public static RoleValidationException getRoleCountLimitException(){
        return new RoleValidationException(Type.ROLE_COUNT_ERROR);
    }

    public static RoleValidationException getRoleAlreadyExistException(){
        return new RoleValidationException(Type.ROLE_ALREADY_EXIST);
    }

    public static RoleValidationException getRoleIsEmptyException(){
        return new RoleValidationException(Type.ROLE_IS_EMPTY);
    }

    public enum Type{
        ROLE_ALREADY_EXIST, ROLE_COUNT_ERROR, ROLE_IS_EMPTY
    }
}
