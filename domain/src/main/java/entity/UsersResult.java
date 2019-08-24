package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UsersResult {

    @Getter
    private Users users;
    @Getter
    private Error error;

    public UsersResult(){
        users = new Users();
    }

    public void addUser(User user){
        users.add(user);
    }

}
