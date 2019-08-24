package dialog;

import controller.Router;
import controller.RouterImpl;
import entity.User;
import presenter.*;
import repository.UserFileRepository;
import repository.UserRepository;

import java.io.InputStream;

public class DialogFactoryImpl implements DialogFactory {

    private static final String DEFAULT_PATH = "users/";

    private InputStream inputStream;
    private UserRepository userRepository;
    private Router router;

    public DialogFactoryImpl(InputStream inputStream){
        this.inputStream = inputStream;
        this.userRepository = new UserFileRepository(DEFAULT_PATH);
        router = new RouterImpl(this);
    }

    @Override
    public CreateUserDialog getCreateUserDialog() {
        CreateUserPresenter presenter = new CreateUserPresenter(router, userRepository);
        CreateUserDialog dialog = new CreateUserDialog(inputStream, presenter);
        presenter.setMvpView(dialog);
        return dialog;
    }

    @Override
    public StartDialog getStartDialog() {
        StartDialogPresenter presenter = new StartDialogPresenter(router);
        StartDialog dialog = new StartDialog(inputStream, presenter);
        presenter.setMvpView(dialog);
        return dialog;
    }

    @Override
    public AllUsersDialog getAllUsers() {
        AllUsersDialogPresenter presenter = new AllUsersDialogPresenter(userRepository, router);
        AllUsersDialog userDialog = new AllUsersDialog(inputStream,
                presenter);
        presenter.setMvpView(userDialog);
        return userDialog;
    }

    @Override
    public UserDialog getUserDialog() {
        UserDialogPresenter presenter = new UserDialogPresenter(userRepository, router);
        UserDialog userDialog = new UserDialog(inputStream, presenter);
        presenter.setMvpView(userDialog);
        return userDialog;
    }

    @Override
    public EditUserDialog getEditUserDialog() {
        EditUserPresenter presenter= new EditUserPresenter(router, userRepository);
        EditUserDialog dialog = new EditUserDialog(inputStream, presenter);
        presenter.setMvpView(dialog);
        return dialog;
    }

    @Override
    public EditUserNameDialog getEditUserFirstNameDialog() {
        EditUserNamePresenter presenter= new EditUserNamePresenter(router);
        EditUserNameDialog dialog = new EditUserNameDialog(inputStream, presenter, EditUserNameDialog.Mode.FIRST_NAME);
        presenter.setMvpView(dialog);
        return dialog;
    }

    @Override
    public EditUserNameDialog getEditUserSecondNameDialog() {
        EditUserNamePresenter presenter= new EditUserNamePresenter(router);
        EditUserNameDialog dialog = new EditUserNameDialog(inputStream, presenter, EditUserNameDialog.Mode.SECOND_NAME);
        presenter.setMvpView(dialog);
        return dialog;
    }


    @Override
    public EditUserPhonesDialog getEditUserPhonesDialog() {
        EditUserPhonePresenter presenter= new EditUserPhonePresenter(router, userRepository);
        EditUserPhonesDialog dialog = new EditUserPhonesDialog(inputStream, presenter);
        presenter.setMvpView(dialog);
        return dialog;
    }

    @Override
    public EditUserEmailDialog getEditUserEmailDialog() {
        EditUserEmailPresenter presenter= new EditUserEmailPresenter(router);
        EditUserEmailDialog dialog = new EditUserEmailDialog(inputStream, presenter);
        presenter.setMvpView(dialog);
        return dialog;
    }

    @Override
    public EditUserRolesDialog getEditUserRolesDialog() {
        EditUserRolesPresenter presenter= new EditUserRolesPresenter(router, userRepository);
        EditUserRolesDialog dialog = new EditUserRolesDialog(inputStream, presenter);
        presenter.setMvpView(dialog);
        return dialog;
    }
}
