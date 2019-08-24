package dialog;

import basic.ExitDialog;
import entity.Phone;
import entity.Role;
import objects.User;

import java.io.InputStream;

public class UserDetailsDialog extends ExitDialog {

    private static final String MESSAGE = "User details";
    private User user;

    public UserDetailsDialog(InputStream inputStream) {
        super(inputStream);
    }

    public void setUser(User user){
        this.user = user;
    }

    @Override
    protected void doBeforeReadCommand() {
        super.doBeforeReadCommand();
        System.out.println(MESSAGE);
        if (user == null) return;
        System.out.println("First name: " + user.getFirstName());
        System.out.println("Second name: " + user.getSecondName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phones: ");
        for (Phone phone : user.getPhones()) {
            System.out.println("      " + phone.getPhone());
        }
        System.out.println("Roles: ");
        for (Role role : user.getRoles()) {
            System.out.println("      " + role.getRole());
        }
    }
}
