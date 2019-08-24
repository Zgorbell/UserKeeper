package presenter.view;


import objects.User;

public interface EditUserMvpView extends MvpView {

    void showUserDetails(User user);

    void showEditVariants();

    void requestNewFirsName();

    void requestNewSecondName();

    void requestNewEmail();
}
