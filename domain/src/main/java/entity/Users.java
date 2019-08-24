package entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<User> users;

    public Users(){
        users = new ArrayList<>();
    }

    public void add(User user){
        users.add(user);
    }

    public User get(int index){
        return users.get(index);
    }

    public int getSize(){
        return users.size();
    }

    public User findUser(SearchArguments arguments) throws UserNotFound{
        if(arguments.fullName != null){
            return findUserByName(arguments.fullName);
        }else if(arguments.number != -1){
            return findUserByNumber(arguments.number);
        }
        throw new UserNotFound();
    }

    private User findUserByName(String name) throws UserNotFound{
        for(User user: users){
            if((user.getFirstName() + " " + user.getSecondName()).equals(name)) return user;
        }
        throw new UserNotFound();
    }

    private User findUserByNumber(int number) throws UserNotFound{
        if(users.size() < number) throw new UserNotFound();
        return users.get(number);
    }

    @Builder
    @AllArgsConstructor
    public static class SearchArguments{

        private String fullName;
        private int number;

        public SearchArguments(){
            number = -1;
        }
    }

    public class UserNotFound extends Exception{

    }
}
