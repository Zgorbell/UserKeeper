package presenter;

import controller.Router;
import entity.Phone;
import error.EmailValidationException;
import error.PhoneValidationException;
import error.UserValidationException;
import objects.User;
import presenter.view.EditUserPhonesMvpView;
import repository.UserRepository;
import share.SharedEditUser;

public class EditUserPhonePresenter extends MvpPresenter<EditUserPhonesMvpView> {

    private User user;
    private int position = -1;
    private UserRepository userRepository;

    public EditUserPhonePresenter(Router router, UserRepository userRepository) {
        super(router);
        this.user = SharedEditUser.getInstance().getUser();
        this.userRepository = userRepository;
    }

    @Override
    public void onViewStarted() {
        view.showRequestPhonePositionMessage();
        view.showPhonesToEdit(user.getPhonesStrings(), 3);
    }

    public void onPhonePositionSelected(int position) {
        this.position = position;
        view.showRequestNewPhoneNumberMessage();
        view.requestNewPhoneNumber();
    }

    public void onNewPhoneEntered(long phone) {
        try {
            Phone phone1 = new Phone();
            phone1.setPhone(phone);
            user.setPhone(phone1, position);
            view.showRequestPhonePositionMessage();
            view.showPhonesToEdit(user.getPhonesStrings(), 3);
        } catch (PhoneValidationException e) {
            if (e.getCode() == PhoneValidationException.PHONE_INVALID) view.showInvalidPhoneError();
            view.showRetryPhoneNumberMessage();
            view.requestNewPhoneNumber();
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
