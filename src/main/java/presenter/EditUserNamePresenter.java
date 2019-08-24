package presenter;

import controller.Router;
import error.UserValidationException;
import objects.User;
import presenter.view.EditUserNameMvpView;
import share.SharedEditUser;

public class EditUserNamePresenter extends MvpPresenter<EditUserNameMvpView> {

    private User user;

    public EditUserNamePresenter(Router router){
        super(router);
        user = SharedEditUser.getInstance().getUser();
    }

    @Override
    public void onViewStarted() {

    }

    public void onFirstNameEntered(String name){
        try {
            user.setFirstName(name);
            view.showNameChangedSuccessful();
            router.startEditUserDialog();
        }catch (UserValidationException e){
            view.showErrorNameIsEmpty();
        }
    }

    public void onSecondNameEntered(String name){
        try {
            user.setSecondName(name);
            view.showNameChangedSuccessful();
            router.startEditUserDialog();
        }catch (UserValidationException e){
            view.showErrorNameIsEmpty();
        }
    }

    public void onExitEntered(){
        router.startEditUserDialog();
    }
}
