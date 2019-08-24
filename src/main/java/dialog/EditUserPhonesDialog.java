package dialog;

import basic.CommandDialog;
import basic.Dialog;
import basic.NumberDialog;
import objects.ChoicesMap;
import presenter.EditUserPhonePresenter;
import presenter.view.EditUserPhonesMvpView;

import java.io.InputStream;
import java.util.List;

public class EditUserPhonesDialog implements Dialog, EditUserPhonesMvpView {

    private static final String EMPTY_PHONE = "empty";
    private static final String MESSAGE_REQUEST_EDIT_PHONE = "Enter number to edit";
    private static final String MESSAGE_REQUEST_NEW_PHONE = "Enter a new phone number";
    private static final String ERROR_INVALID_PHONE = "Phone number is invalid";
    private static final String RETRY_MESSAGE = "Try again";

    private static final String SAVE_COMMAND = "Save user";

    private NumberDialog numberDialog;
    private CommandDialog commandDialog;
    private EditUserPhonePresenter presenter;
    private ChoicesMap choicesMap;

    public EditUserPhonesDialog(InputStream inputStream, EditUserPhonePresenter presenter) {
        this.numberDialog = new NumberDialog(inputStream);
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
    public void requestNewPhoneNumber() {
        numberDialog.start();
    }

    @Override
    public void showPhonesToEdit(List<String> phones, int maxCount) {
        for (int i = 0; i < maxCount; i++) {
            choicesMap.addChoice(i, phones.size() > i ? phones.get(i) : EMPTY_PHONE);
        }
        choicesMap.addChoice(maxCount + 1, SAVE_COMMAND);
        commandDialog.start();
    }

    @Override
    public void showRequestNewPhoneNumberMessage() {
        System.out.println(MESSAGE_REQUEST_NEW_PHONE);
    }

    @Override
    public void showRequestPhonePositionMessage() {
        System.out.println(MESSAGE_REQUEST_EDIT_PHONE);
    }

    @Override
    public void showInvalidPhoneError() {
        System.out.println(ERROR_INVALID_PHONE);
    }

    @Override
    public void showRetryPhoneNumberMessage() {
        System.out.println(RETRY_MESSAGE);
    }

    private void setCommandDialogListener() {
        commandDialog.setListener(new CommandDialog.CommandDialogListener() {
            @Override
            public void onCommandSelected(String command) {
                if (command.equals(SAVE_COMMAND)) presenter.onSaveSelected();
                else presenter.onPhonePositionSelected(choicesMap.getPosition(command));
            }

            @Override
            public void onExitEntered() {
                presenter.onExitEntered();
            }
        });
    }


    private void setPhoneDialogListener() {

        numberDialog.setListener(new NumberDialog.NumberDialogListener() {
            @Override
            public void onNumberEntered(long number) {
                presenter.onNewPhoneEntered(number);
            }

            @Override
            public void onExitEntered() {
                presenter.onExitEntered();
            }
        });
    }


}
