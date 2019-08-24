package dialog;

import basic.CommandDialog;
import basic.Dialog;
import basic.ExitDialog;
import entity.Phone;
import entity.Role;
import objects.ChoicesMap;
import objects.User;
import presenter.UserDialogPresenter;

import java.io.InputStream;

public class UserDialog implements Dialog, presenter.view.UserMvpView {

    private static final String DELETE_USER = "Delete user";
    private static final String EDIT_USER = "Edit user";
    private static final String SHOW_DETAILS = "Show user details";

    private static final String USER_ALREADY_DELETED = "User already deleted";

    private final ExitDialog exitDialog;
    private final UserDialogPresenter presenter;
    private final UserDetailsDialog userDetailsDialog;
    private final CommandDialog commandDialog;

    public UserDialog(InputStream inputStream,
                      UserDialogPresenter userDialogPresenter) {
        this.exitDialog = new ExitDialog(inputStream);
        this.commandDialog =  new CommandDialog(inputStream);
        this.userDetailsDialog = new UserDetailsDialog(inputStream);
        this.presenter = userDialogPresenter;
        setCommandDialogListener();
        setExitDialogListener();
    }

    public void start() {
        commandDialog.setChoicesMap(getChoices());
        setCommandDialogListener();
        presenter.onViewStarted();
    }

    private ChoicesMap getChoices() {
        ChoicesMap list = new ChoicesMap();
        list.addChoice(0, SHOW_DETAILS);
        list.addChoice(1, EDIT_USER);
        list.addChoice(2, DELETE_USER);
        return list;
    }

    @Override
    public void showCommandDialog() {
        commandDialog.start();
    }

    @Override
    public void showDeleteSuccess() {
        System.out.println("User has been deleted successful");
    }

    @Override
    public void showDeleteWrong() {
        System.out.println("Something wrong");
    }

    @Override
    public void showMessageAlreadyDeleted() {
        System.out.println(USER_ALREADY_DELETED);
    }

    private void setCommandDialogListener() {
        commandDialog.setListener(new CommandDialog.CommandDialogListener() {
            @Override
            public void onCommandSelected(String command) {
                switch (command) {
                    case DELETE_USER:
                        presenter.onUserDeleteChecked();
                        break;
                    case EDIT_USER:
                        presenter.onEditUserChecked();
                        break;
                    case SHOW_DETAILS:
                        presenter.onShowUserDetailsChecked();
                }
            }

            @Override
            public void onExitEntered() {
                presenter.onExitSelected();
            }
        });
    }

    public void setExitDialogListener(){
        userDetailsDialog.setListener(presenter::onExitFromUserDetailsEntered);
    }

    @Override
    public void showUserDetails(User user) {
        userDetailsDialog.setUser(user);
        userDetailsDialog.start();
    }

    @Override
    public void requestExit() {
        exitDialog.start();
    }
}
