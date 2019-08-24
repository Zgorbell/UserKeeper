package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DeleteUserResult {


    public static final String SUCCESS = "success";
    public static final String ALREADY_DELETED = "deleted";
    public static final String ERROR = "error";

    @Getter
    private String status;
}
