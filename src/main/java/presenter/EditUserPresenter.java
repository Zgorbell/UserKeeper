package presenter;


import controller.Router;
import error.EmailValidationException;
import error.UserValidationException;
import objects.User;
import presenter.view.EditUserMvpView;
import repository.UserRepository;
import share.SharedEditUser;

public class EditUserPresenter extends MvpPresenter<EditUserMvpView> {

    private final User user;
    private final UserRepository repository;

    public EditUserPresenter(final Router router, final UserRepository userRepository) {
        super(router);
        user = SharedEditUser.getInstance().getUser();
        this.repository = userRepository;
    }

    @Override
    public void onViewStarted() {
        view.showUserDetails(user);
        view.showEditVariants();
    }

    public void onFirstNameEditSelected() {
        router.startEditFirstNameDialog();
    }

    public void onSecondNameEditSelected() {
        router.startEditSecondNameDialog();
    }

    public void onEmailEditSelected() {
        router.startEditEmailDialog();
    }

    public void onPhoneEditSelected() {
        router.startEditPhoneDialog();
    }

    public void onRoleEditSelected() {
        router.startEditRolesDialog();
    }

    public void onExitSelected(){
        router.startUserDialog();
    }

    public void onSaveUserSelected(){
        try {
            repository.save(user.toDomain());
            router.startAllUsersDialog();
        }catch (UserValidationException e){

        } catch (EmailValidationException e){

        }
    }
}
