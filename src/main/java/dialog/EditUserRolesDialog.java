package dialog;

import basic.CommandDialog;
import basic.Dialog;
import basic.ExitDialog;
import basic.NumberDialog;
import javafx.scene.control.TextInputDialog;
import objects.ChoicesMap;
import presenter.EditUserPhonePresenter;
import presenter.EditUserRolesPresenter;
import presenter.view.EditUserRolesMvpView;

import java.io.InputStream;
import java.util.List;

public class EditUserRolesDialog implements Dialog, EditUserRolesMvpView {

    private static final String EMPTY_ROLE = "empty";
    private static final String MESSAGE_REQUEST_POSITION_ROLE = "Enter number of role to edit";
    private static final String MESSAGE_REQUEST_NEW_ROLE = "Enter a new role";
    private static final String ERROR_INVALID_ROLE = "Role is invalid";
    private static final String RETRY_MESSAGE = "Try again";

    private static final String SAVE_COMMAND = "Save user";

    private final CommandDialog commandDialog;
    private final EditUserRolesPresenter presenter;
    private final ChoicesMap choicesMap;
    private final ExitDialog textDialog;

    public EditUserRolesDialog(InputStream inputStream, EditUserRolesPresenter presenter) {
        this.textDialog = new NumberDialog(inputStream);
        this.commandDialog = new CommandDialog(inputStream);
        this.presenter = presenter;
        this.choicesMap = new ChoicesMap();
        commandDialog.setChoicesMap(choicesMap);
        setCommandDialogListener();
        setPhoneDialogListener();
    }

    @Override
    public void start() {
        presenter.onViewStarted();
    }

    @Override
    public void requestNewRole() {
        textDialog.start();
    }

    @Override
    public void showRequestNewRoleMessage() {
        System.out.println(MESSAGE_REQUEST_NEW_ROLE);
    }

    @Override
    public void showRequestRolePositionMessage() {
        System.out.println(MESSAGE_REQUEST_POSITION_ROLE);
    }

    @Override
    public void showInvalidRoleError() {
        System.out.println(ERROR_INVALID_ROLE);
    }

    @Override
    public void showRetryRoleNumberMessage() {
        System.out.println(RETRY_MESSAGE);
    }

    @Override
    public void showErrorRoleIsEmpty() {

    }

    @Override
    public void showErrorRoleIsAlreadyExist() {

    }

    @Override
    public void showErrorRoleLimit() {

    }

    @Override
    public void showRolesToEdit(List<String> phones, int maxCount) {
        for (int i = 0; i < maxCount; i++) {
            choicesMap.addChoice(i, phones.size() > i ? phones.get(i) : EMPTY_ROLE);
        }
        choicesMap.addChoice(maxCount + 1, SAVE_COMMAND);
        commandDialog.start();
    }

    private void setCommandDialogListener() {
        commandDialog.setListener(new CommandDialog.CommandDialogListener() {
            @Override
            public void onCommandSelected(String command) {
                if (command.equals(SAVE_COMMAND)) presenter.onSaveSelected();
                else presenter.onRolePositionSelected(choicesMap.getPosition(command));
            }

            @Override
            public void onExitEntered() {
                presenter.onExitEntered();
            }
        });
    }


    private void setPhoneDialogListener() {
        textDialog.setListener(presenter::onExitEntered);

        textDialog.setStringListener(presenter::onNewRoleEntered);
    }
}
