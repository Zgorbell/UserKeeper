package presenter.view;

import objects.User;

public interface UserMvpView extends MvpView{

    void showCommandDialog();

    void showDeleteSuccess();

    void showDeleteWrong();

    void showUserDetails(User user);

    void requestExit();

    void showMessageAlreadyDeleted();
}
