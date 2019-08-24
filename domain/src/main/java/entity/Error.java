package entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Error {

    public static final String READ_ERROR = "read_error";

    private String error;

    public static Error getReadError(){
        return new Error(READ_ERROR);
    }
}
