package dialog;

import basic.CommandDialog;
import basic.Dialog;
import objects.ChoicesMap;
import presenter.StartDialogPresenter;
import presenter.view.StartDialogMvpView;

import java.io.InputStream;

public class StartDialog implements Dialog, StartDialogMvpView {

    public static final int CREATE_USER_COMMAND = 0;
    public static final int SHOW_USERS_COMMAND = 1;
    public static final int CLOSE_PROGRAM_COMMAND = 2;

    private static final String CREATE_USER_MESSAGE = "Create a new user";
    private static final String SHOW_USERS_MESSAGE = "Show all users";
    private static final String CLOSE_PROGRAM_MESSAGE = "Close program";
    private final CommandDialog commandDialog;
    private final StartDialogPresenter dialogPresenter;

    public StartDialog(InputStream inputStream, StartDialogPresenter startDialogPresenter) {
        commandDialog = new CommandDialog(inputStream);
        dialogPresenter = startDialogPresenter;
        commandDialog.setChoicesMap(getChoices());
        setDialogListener();
    }

    public void start() {
        dialogPresenter.onViewStarted();
    }

    private ChoicesMap getChoices() {
        ChoicesMap choicesMap = new ChoicesMap();
        choicesMap.addChoice(CREATE_USER_COMMAND, CREATE_USER_MESSAGE);
        choicesMap.addChoice(SHOW_USERS_COMMAND, SHOW_USERS_MESSAGE);
        choicesMap.addChoice(CLOSE_PROGRAM_COMMAND, CLOSE_PROGRAM_MESSAGE);
        return choicesMap;
    }

    @Override
    public void showChoices() {
        commandDialog.start();
    }

    private void setDialogListener() {
        commandDialog.setListener(new CommandDialog.CommandDialogListener(){
            @Override
            public void onCommandSelected(String command) {
                switch (command) {
                    case CREATE_USER_MESSAGE:
                        dialogPresenter.onCreateUserSelected();
                        break;
                    case SHOW_USERS_MESSAGE:
                        dialogPresenter.onShowAllUsersSelected();
                        break;
                    case CLOSE_PROGRAM_MESSAGE:
                        dialogPresenter.onCloseProgramSelected();
                }
            }

            @Override
            public void onExitEntered() {
                dialogPresenter.onExitSelected();
            }
        } );
    }
}
