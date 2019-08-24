package presenter.view;

import entity.Users;

public interface AllUsersMvpView extends MvpView {

    void showAllUsers(Users users);

    void showErrorReadUser();

    void showMessageRequestCommand();

    void requestCommand();

    void showMessageUsersNotRegistered();

    void askAnyKeyToExit();
}
