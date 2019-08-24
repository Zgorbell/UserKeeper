package presenter.view;

import entity.Phone;

import java.util.List;

public interface EditUserPhonesMvpView extends MvpView{

    void showPhonesToEdit(List<String> phones, int maxCount);

    void requestNewPhoneNumber();

    void showRequestNewPhoneNumberMessage();

    void showRequestPhonePositionMessage();

    void showInvalidPhoneError();

    void showRetryPhoneNumberMessage();
}
