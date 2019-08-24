package dialog;

import basic.Dialog;
import basic.ExitDialog;
import basic.NumberDialog;
import entity.Phone;
import entity.Role;
import objects.User;
import presenter.CreateUserPresenter;
import presenter.view.CreateUserMvpView;

import java.io.InputStream;
import java.util.Scanner;

public class CreateUserDialog implements Dialog, CreateUserMvpView {

    private static final String EXIT = "exit";

    private static final String CREATE_NEW_USER = "Create a new User Dialog";

    public static final String ENTER_FIRST_NAME = "Enter first name";
    public static final String ENTER_SECOND_NAME = "Enter second name";
    public static final String ENTER_PHONE_NUMBER = "Enter phone numbers, limit is 3 phones";
    public static final String ENTER_EMAIL = "Enter email";
    public static final String TRY_AGAIN = "Try again";
    public static final String ENTER_ROLE = "Enter role";

    private static final String MESSAGE_PHONES_LIMIT = "Count of phones reached limit";
    private static final String MESSAGE_ROLES_LIMIT = "Count of roles reached limit";

    private final Scanner scanner;
    private final NumberDialog numberDialog;
    private final ExitDialog exitDialog;
    private final CreateUserPresenter presenter;

    public CreateUserDialog(final InputStream inputStream, final CreateUserPresenter presenter) {
        this.presenter = presenter;
        numberDialog = new NumberDialog(inputStream);
        exitDialog = new ExitDialog(inputStream);
        scanner = new Scanner(inputStream);
    }

    public void start() {
        System.out.println("To exit, enter \"exit\"");
        presenter.onViewStarted();
    }

    @Override
    public void askMoreOnePhone() {
        System.out.println("Do you want to add more one phone number? Enter y/n");
        final String nextLine = scanner.nextLine();
        if (nextLine.toUpperCase().equals("Y") || nextLine.toUpperCase().equals("YES"))
            presenter.onMoreOnePhoneEnteredPositive();
        else if (nextLine.toUpperCase().equals("N") || nextLine.toUpperCase().equals("NO"))
            presenter.onMoreOnePhoneEnteredNegative();
        else askMoreOnePhone();
    }

    @Override
    public void askMoreOneRole() {
        System.out.println("Do you want to add more one role? Enter y/n");
        final String nextLine = scanner.nextLine();
        if (nextLine.toUpperCase().equals("Y") || nextLine.toUpperCase().equals("YES"))
            presenter.onMoreOneRoleEnteredPositive();
        else if (nextLine.toUpperCase().equals("N") || nextLine.toUpperCase().equals("NO"))
            presenter.onMoreOneRoleEnteredNegative();
        else askMoreOneRole();
    }

    @Override
    public void askToSaveUser() {
        System.out.println("Do you want to save user, enter y/n");
        final String nextLine = scanner.nextLine();
        if (nextLine.toUpperCase().equals("Y") || nextLine.toUpperCase().equals("YES")) presenter.onUserSaveAllow();
        else if (nextLine.toUpperCase().equals("N") || nextLine.toUpperCase().equals("NO")) presenter.onUserSaveDeny();
        else {
            System.out.println("Invalid answer. Try again");
            askToSaveUser();
        }
    }

    @Override
    public void showUserStatus(User user) {
        System.out.println("First name: " + user.getFirstName());
        System.out.println("Second name: " + user.getSecondName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phones: ");
        for (Phone phone : user.getPhones()) {
            System.out.println(phone.getPhone());
        }
        System.out.println("Roles: ");
        for (Role role : user.getRoles()) {
            System.out.println(role.getRole());
        }
    }

    @Override
    public void showMessageRequestFirstName() {
        System.out.println(ENTER_FIRST_NAME);
    }

    @Override
    public void showMessageRequestSecondName() {
        System.out.println(ENTER_SECOND_NAME);
    }

    @Override
    public void showMessageRequestEmail() {
        System.out.println(ENTER_EMAIL);
    }

    @Override
    public void showMessageRequestPhone() {
        System.out.println(ENTER_PHONE_NUMBER);
    }

    @Override
    public void showMessageRequestRole() {
        System.out.println(ENTER_ROLE);
    }

    @Override
    public void showMessageRequestSaveUser() {

    }

    @Override
    public void showMessagePhonesLimitReached() {
        System.out.println(MESSAGE_PHONES_LIMIT);
    }

    @Override
    public void showMessageRolesLimitReached() {
        System.out.println(MESSAGE_ROLES_LIMIT);
    }

    @Override
    public void requestFirstName() {
        exitDialog.setStringListener(presenter::onFirstNameEntered);
        exitDialog.setListener(presenter::onExitEntered);
        exitDialog.start();
    }

    @Override
    public void requestSecondName() {
        exitDialog.setStringListener(presenter::onSecondNameEntered);
        exitDialog.setListener(presenter::onExitEntered);
        exitDialog.start();
    }

    @Override
    public void requestEmail() {
        exitDialog.setStringListener(presenter::onEmailEntered);
        exitDialog.setListener(presenter::onExitEntered);
        exitDialog.start();
    }

    @Override
    public void requestPhone() {
        numberDialog.setListener(new NumberDialog.NumberDialogListener() {
            @Override
            public void onNumberEntered(long number) {
                presenter.onPhoneEntered(number);
            }

            @Override
            public void onExitEntered() {
                presenter.onExitEntered();
            }
        });
        numberDialog.start();
    }

    @Override
    public void requestRole() {
        exitDialog.setStringListener(presenter::onRoleEntered);
        exitDialog.setListener(presenter::onExitEntered);
        exitDialog.start();
    }

    @Override
    public void showErrorFirstNameIsEmpty() {
        System.out.println("First Name is empty " + TRY_AGAIN);
    }

    @Override
    public void showErrorSecondNameIsEmpty() {
        System.out.println("Second Name is empty " + TRY_AGAIN);
    }

    @Override
    public void showErrorEmailIsEmpty() {
        System.out.println("Empty email " + TRY_AGAIN);
    }

    @Override
    public void showErrorEmailIsInvalid() {
        System.out.println("Email is invalid. Try format ****@****.***");
    }

    @Override
    public void showErrorPhoneAlreadyExist() {
        System.out.println("Phone number is already added");
    }

    @Override
    public void showErrorPhoneInvalid() {
        System.out.println("Phone number is invalid");
    }

    @Override
    public void showErrorRoleIsEmpty() {
        System.out.println("Role is empty");
    }

    @Override
    public void showErrorRoleIsAlreadyExist() {
        System.out.println("Role is already added");
    }

    @Override
    public void showErrorPhonesLimit() {

    }
}
