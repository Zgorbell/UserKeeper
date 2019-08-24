package presenter.view;

import objects.User;

public interface CreateUserMvpView extends MvpView {

    void showMessageRequestFirstName();

    void showMessageRequestSecondName();

    void showMessageRequestEmail();

    void showMessageRequestPhone();

    void askMoreOnePhone();

    void showMessageRequestRole();

    void askMoreOneRole();

    void showMessageRequestSaveUser();

    void showMessagePhonesLimitReached();

    void showMessageRolesLimitReached();

    void requestFirstName();

    void requestSecondName();

    void requestEmail();

    void requestPhone();

    void requestRole();

    void askToSaveUser();

    void showErrorFirstNameIsEmpty();

    void showErrorSecondNameIsEmpty();

    void showErrorEmailIsEmpty();

    void showErrorEmailIsInvalid();

    void showErrorPhoneAlreadyExist();

    void showErrorPhoneInvalid();

    void showErrorRoleIsEmpty();

    void showErrorRoleIsAlreadyExist();

    void showErrorPhonesLimit();

    void showUserStatus(User user);
}
