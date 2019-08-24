package presenter;

import controller.Router;
import entity.DeleteUserResult;
import error.EmailValidationException;
import error.UserValidationException;
import objects.User;
import presenter.view.UserMvpView;
import repository.UserRepository;
import share.SharedEditUser;

public class UserDialogPresenter extends MvpPresenter<UserMvpView> {

    private User user;
    private UserRepository userRepository;

    public UserDialogPresenter(UserRepository userRepository, Router router) {
        super(router);
        this.user = SharedEditUser.getInstance().getUser();
        this.userRepository = userRepository;
    }

    @Override
    public void onViewStarted() {
        view.showCommandDialog();
    }

    public void onUserDeleteChecked() {
        try {
            DeleteUserResult result = userRepository.deleteUser(user.toDomain());
            switch (result.getStatus()) {
                case DeleteUserResult.SUCCESS:
                    view.showDeleteSuccess();
                    break;
                case DeleteUserResult.ERROR:
                    view.showDeleteWrong();
                    break;
                case DeleteUserResult.ALREADY_DELETED:
                    view.showMessageAlreadyDeleted();
                    break;
            }
            router.startAllUsersDialog();
        } catch (UserValidationException e) {

        } catch (EmailValidationException e) {

        }
    }

    public void onEditUserChecked() {
        SharedEditUser.getInstance().setUser(user);
        router.startEditUserDialog();
    }

    public void onShowUserDetailsChecked(){
        view.showUserDetails(user);
        view.requestExit();
    }

    public void onExitFromUserDetailsEntered(){
        view.showCommandDialog();
    }

    public void onExitSelected() {
        router.startAllUsersDialog();
    }
}
