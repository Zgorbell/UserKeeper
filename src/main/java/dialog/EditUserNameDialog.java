package dialog;

import basic.Dialog;
import basic.ExitDialog;
import presenter.EditUserNamePresenter;
import presenter.view.EditUserNameMvpView;

import java.io.InputStream;

public class EditUserNameDialog extends ExitDialog implements Dialog, EditUserNameMvpView {

    private static final String EMPTY_NAME_MESSAGE = "Name is empty. Try again.";
    private static final String ENTER_FIRST_NAME = "Enter first name";
    private static final String ENTER_SECOND_NAME = "Enter second name";

    private final Mode mode;
    private final EditUserNamePresenter presenter;

    public EditUserNameDialog(final InputStream inputStream, final EditUserNamePresenter presenter, final Mode mode) {
        super(inputStream);
        this.presenter = presenter;
        this.mode = mode;
    }

    @Override
    protected void onExitEntered() {
        presenter.onExitEntered();
    }

    @Override
    protected void doBeforeReadCommand() {
        if(mode == Mode.FIRST_NAME) System.out.println(ENTER_FIRST_NAME);
        else System.out.println(ENTER_SECOND_NAME);
    }

    @Override
    protected void resolveInput() {
        super.resolveInput();
        if(mode == Mode.FIRST_NAME) presenter.onFirstNameEntered(getInput());
        else presenter.onSecondNameEntered(getInput());
    }

    @Override
    public void showErrorNameIsEmpty() {
        System.out.println(EMPTY_NAME_MESSAGE);
    }

    @Override
    public void showNameChangedSuccessful() {
        setResolved();
    }

    public enum Mode{
        FIRST_NAME, SECOND_NAME
    }
}
