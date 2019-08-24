package presenter.view;

public interface EditUserEmailMvpView extends MvpView{

    void requestEmail();

    void showCurrentEmail(String email);

    void showInvalidEmailError();
}
