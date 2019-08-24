package presenter;

import controller.Router;
import error.EmailValidationException;
import error.UserValidationException;
import objects.User;
import presenter.view.EditUserEmailMvpView;
import share.SharedEditUser;

public class EditUserEmailPresenter extends MvpPresenter<EditUserEmailMvpView> {

    private User user;

    public EditUserEmailPresenter(Router router) {
        super(router);
        this.user = SharedEditUser.getInstance().getUser();
    }

    @Override
    public void onViewStarted() {
        view.showCurrentEmail(user.getEmail());
        view.requestEmail();
    }

    public void onEmailEntered(String email) {
        try {
            user.setEmail(email);
            router.startEditUserDialog();
        } catch (EmailValidationException e){
            view.showInvalidEmailError();
        }
    }

    public void onExitEntered() {
        router.startEditUserDialog();
    }
}
