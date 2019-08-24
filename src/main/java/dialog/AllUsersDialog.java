package dialog;

import basic.CommandDialog;
import basic.Dialog;
import entity.Users;
import presenter.AllUsersDialogPresenter;
import presenter.view.AllUsersMvpView;
import repository.UserRepository;

import java.io.InputStream;
import java.util.Scanner;

public class AllUsersDialog implements Dialog, AllUsersMvpView {

    private static final String EXIT = "exit";


    private final Scanner scanner;
    private String input;
    private final AllUsersDialogPresenter presenter;
    private final CommandDialog commandDialog;

    public AllUsersDialog(InputStream inputStream,
                          AllUsersDialogPresenter presenter) {
        commandDialog = new CommandDialog(inputStream);
        scanner = new Scanner(inputStream);
        this.presenter = presenter;
        input = "";
    }

    public void start() {
        presenter.onViewStarted();
    }



    @Override
    public void requestCommand() {
        readNextLine();
        resolveInput();
    }

    @Override
    public void showMessageRequestCommand() {
        System.out.println("Enter number to show more info or enter \"exit\" to close dialog.");
    }

    private void readNextLine() {
        input = scanner.nextLine();
    }

    private void resolveInput() {
        if (input.equals(EXIT)) presenter.onExitEntered();
        else if (isInputInteger()) presenter.onUserSelected(Integer.parseInt(input));
        else presenter.onUserSelected(input);
    }

    private boolean isInputInteger() {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public void showAllUsers(Users users) {
        for (int i = 0; i < users.getSize(); i++) {
            System.out.printf("[%d] %s %s \n", i, users.get(i).getFirstName(), users.get(i).getSecondName());
        }
    }

    @Override
    public void showErrorReadUser() {
        System.out.println("Users not found");
    }

    @Override
    public void showMessageUsersNotRegistered() {
        System.out.println("Users are not registered");
    }

    @Override
    public void askAnyKeyToExit() {
        System.out.println("Enter any key to return to start menu");
        scanner.nextLine();
        presenter.onExitEntered();
    }
}
