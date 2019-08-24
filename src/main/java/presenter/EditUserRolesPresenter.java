package presenter;

import controller.Router;
import entity.Role;
import error.EmailValidationException;
import error.RoleValidationException;
import error.UserValidationException;
import objects.User;
import presenter.view.EditUserRolesMvpView;
import repository.UserRepository;
import share.SharedEditUser;

public class EditUserRolesPresenter extends MvpPresenter<EditUserRolesMvpView> {

    private User user;
    private int position = -1;
    private UserRepository userRepository;

    public EditUserRolesPresenter(Router router, UserRepository userRepository) {
        super(router);
        this.user = SharedEditUser.getInstance().getUser();
        this.userRepository = userRepository;
    }

    @Override
    public void onViewStarted() {
        view.showRequestRolePositionMessage();
        view.showRolesToEdit(user.getRolesStrings(), 3);
    }

    public void onRolePositionSelected(int position) {
        this.position = position;
        view.showRequestNewRoleMessage();
        view.requestNewRole();
    }

    public void onNewRoleEntered(String newRole) {
        try {
            Role role = new Role();
            role.setRole(newRole);
            user.setRole(role, position);
            onViewStarted();
        } catch (RoleValidationException e) {
            switch (e.getType()) {
                case ROLE_IS_EMPTY:
                    view.showErrorRoleIsEmpty();
                    break;
                case ROLE_ALREADY_EXIST:
                    view.showErrorRoleIsAlreadyExist();
                    break;
                case ROLE_COUNT_ERROR:
                    view.showErrorRoleLimit();
                    break;
            }
        }
    }

    public void onSaveSelected() {
        try {
            userRepository.save(user.toDomain());
            router.startEditUserDialog();
        } catch (UserValidationException e) {

        } catch (EmailValidationException e) {

        }
    }

    @Override
    public void onExitEntered() {
        router.startEditUserDialog();
    }
}
