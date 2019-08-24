package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {

    private int primaryKey;

    private String firstName;

    private String secondName;

    private String email;

    private Roles roles;

    private Phones phones;


    public User(){

    }
}
