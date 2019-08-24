package objects;

import entity.Phone;
import entity.Phones;
import entity.Role;
import entity.Roles;
import error.EmailValidationException;
import error.PhoneValidationException;
import error.RoleValidationException;
import error.UserValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
public class User {

    private int primaryKey;

    @Getter
    private String firstName;

    @Getter
    private String secondName;

    @Getter
    private String email;

    private Roles roles;

    private Phones phones;

    public User(entity.User user){
        primaryKey = user.getPrimaryKey();
        firstName = user.getFirstName();
        secondName = user.getSecondName();
        email = user.getEmail();
        roles = user.getRoles();
        phones = user.getPhones();
    }

    public User(){
        roles = new Roles();
        phones = new Phones();
    }

    public void setFirstName(String firstName) throws UserValidationException {
        if(firstName == null || firstName.isEmpty()) throw UserValidationException.getFirstNameEmpty();
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) throws UserValidationException {
        if(secondName == null || secondName.isEmpty()) throw UserValidationException.getSecondNameEmpty();
        this.secondName = secondName;
    }

    public void setEmail(String email) throws EmailValidationException{
        this.email = email;
        if(!isEmailValid()) throw EmailValidationException.getEmailInvalidException();
    }

    public void addRole(Role role) throws RoleValidationException {
        roles.add(role);
    }

    public void setRole(Role role, int position){
        try {
            roles.set(role, position);
        }catch (RoleValidationException e){

        }
    }

    public void addPhone(Phone phone) throws PhoneValidationException {
        phones.add(phone);
    }

    public void setPhone(Phone phone, int position){
        phones.set(phone, position);
    }

    public List<Phone> getPhones(){
        return phones.getPhones();
    }

    public List<Role> getRoles(){
        return roles.getRoles();
    }

    public List<String> getRolesStrings(){
        return roles.getRolesStrings();
    }

    public entity.User toDomain() throws UserValidationException, EmailValidationException  {
        if(firstName == null || firstName.equals(""))
            throw UserValidationException.getFirstNameEmpty() ;
        if(secondName == null || secondName.equals(""))
            throw UserValidationException.getSecondNameEmpty();
        if(email == null) throw EmailValidationException.getEmailEmpty();
        if(!isEmailValid()) throw  EmailValidationException.getEmailInvalidException();
        return mapToDomain();
    }

    public boolean isPhoneInLimit(){
        return phones.size() == 3;
    }

    public boolean isRoleInLimit(){
        return roles.size() == 3;
    }

    public List<String> getPhonesStrings(){
        return phones.getPhonesStrings();
    }

    private boolean isEmailValid(){
        int dogPosition = -1;
        int dotPosition = -1;
        char[] chars = email.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == '@') dogPosition = i;
            if(chars[i] == '.') dotPosition = i;
        }

        return dotPosition != -1 && dogPosition != -1 && dogPosition < dotPosition + 1;
    }

    private entity.User mapToDomain(){
        return new entity.User(primaryKey, firstName, secondName, email, roles, phones);
    }
}
