package presenter;

import controller.Router;
import entity.Phone;
import entity.Role;
import error.EmailValidationException;
import error.PhoneValidationException;
import error.RoleValidationException;
import error.UserValidationException;
import objects.User;
import presenter.view.CreateUserMvpView;
import repository.UserRepository;

public class CreateUserPresenter extends MvpPresenter<CreateUserMvpView> {

    private final UserRepository userRepository;
    private final User user;

    public CreateUserPresenter(Router router, UserRepository userRepository) {
        super(router);
        this.userRepository = userRepository;
        user = new User();
    }

    @Override
    public void onViewStarted() {
        view.showMessageRequestFirstName();
        view.requestFirstName();
    }

    public void onFirstNameEntered(final String firstName) {
        try {
            user.setFirstName(firstName);
            view.showMessageRequestSecondName();
            view.requestSecondName();
        } catch (UserValidationException e) {
            view.showErrorFirstNameIsEmpty();
            view.showMessageRequestFirstName();
            view.requestFirstName();
        }
    }

    public void onSecondNameEntered(final String secondName) {
        try {
            user.setSecondName(secondName);
            view.showMessageRequestEmail();
            view.requestEmail();
        } catch (UserValidationException e) {
            view.showErrorSecondNameIsEmpty();
            view.showMessageRequestSecondName();
            view.requestSecondName();
        }
    }

    public void onEmailEntered(final String email) {
        try {
            user.setEmail(email);
            view.showMessageRequestPhone();
            view.requestPhone();
        } catch (EmailValidationException e) {
            switch (e.getType()) {
                case EMAIL_INVALID:
                    view.showErrorEmailIsInvalid();
                    break;
                case EMAIL_EMPTY:
                    view.showErrorEmailIsEmpty();
                    break;
            }
            view.showMessageRequestEmail();
            view.requestEmail();
        }
    }

    public void onPhoneEntered(final long phone) {
        try {
            user.addPhone(new Phone().setPhone(phone));
        } catch (PhoneValidationException e) {
            switch (e.getCode()) {
                case PhoneValidationException.PHONE_ALREADY_EXIST:
                    view.showErrorPhoneAlreadyExist();
                    break;
                case PhoneValidationException.PHONE_INVALID:
                    view.showErrorPhoneInvalid();
                    break;
                case PhoneValidationException.PHONEs_COUNT_ERROR:
                    break;
            }
        }
        if(!user.isPhoneInLimit()){
            view.askMoreOnePhone();
        }else{
            view.showMessagePhonesLimitReached();
            onMoreOnePhoneEnteredNegative();
        }
    }

    public void onRoleEntered(final String role) {
        try {
            user.addRole(new Role().setRole(role));
        } catch (RoleValidationException e) {
            switch (e.getType()) {
                case ROLE_COUNT_ERROR:
                    break;
                case ROLE_ALREADY_EXIST:
                    view.showErrorRoleIsAlreadyExist();
                    break;
                case ROLE_IS_EMPTY: view.showErrorRoleIsEmpty();
                break;
            }
        }
        if(!user.isRoleInLimit()){
            view.askMoreOneRole();
        } else{
            view.showMessageRolesLimitReached();
            onMoreOneRoleEnteredNegative();
        }

    }

    public void onUserSaveAllow() {
        try {
            userRepository.save(user.toDomain());
            router.startStartDialog();
        } catch (UserValidationException e) {
            System.out.println("Error : " + e.getType());
        } catch (EmailValidationException e) {

        }
    }

    public void onUserSaveDeny(){
        router.startStartDialog();
    }

    public void onMoreOnePhoneEnteredPositive(){
        view.showMessageRequestPhone();
        view.requestPhone();
    }

    public void onMoreOnePhoneEnteredNegative(){
        view.showMessageRequestRole();
        view.requestRole();
    }

    public void onMoreOneRoleEnteredPositive(){
        view.showMessageRequestRole();
        view.requestRole();
    }

    public void onMoreOneRoleEnteredNegative(){
        view.askToSaveUser();
    }

    @Override
    public void onExitEntered() {
        router.startStartDialog();
    }
}
