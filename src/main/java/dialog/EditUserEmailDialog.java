package dialog;

import basic.Dialog;
import basic.ExitDialog;
import presenter.EditUserEmailPresenter;
import presenter.view.EditUserEmailMvpView;

import java.io.InputStream;

public class EditUserEmailDialog extends ExitDialog implements Dialog, EditUserEmailMvpView {

    private static final String CURRENT_EMAIL_MESSAGE = "Current email:";
    private static final String EMAIL_REQUEST = "Enter a new email.";
    private static final String EMAIL_INVALID_ERROR = "Email invalid error. Try again.";
    private EditUserEmailPresenter presenter;

    public EditUserEmailDialog(InputStream inputStream, EditUserEmailPresenter presenter) {
        super(inputStream);
        this.presenter = presenter;
    }

    @Override
    public void start() {
        presenter.onViewStarted();
    }

    @Override
    public void requestEmail() {
        System.out.println(EMAIL_REQUEST);
        super.start();
    }

    @Override
    protected void resolveInput() {
        super.resolveInput();
        presenter.onEmailEntered(getInput());
    }

    @Override
    protected void onExitEntered() {
        super.onExitEntered();
        presenter.onExitEntered();
    }

    @Override
    public void showInvalidEmailError() {
        System.out.println(EMAIL_INVALID_ERROR);
    }

    @Override
    public void showCurrentEmail(String email) {
        System.out.printf("%s %s \n" , CURRENT_EMAIL_MESSAGE, email);
    }
}
