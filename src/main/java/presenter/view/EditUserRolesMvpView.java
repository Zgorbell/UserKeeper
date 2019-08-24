package presenter.view;

import java.util.List;

public interface EditUserRolesMvpView extends MvpView{

    void showRolesToEdit(List<String> roles, int maxCount);

    void requestNewRole();

    void showRequestNewRoleMessage();

    void showRequestRolePositionMessage();

    void showInvalidRoleError();

    void showRetryRoleNumberMessage();

    void showErrorRoleIsEmpty();

    void showErrorRoleIsAlreadyExist();

    void showErrorRoleLimit();
}
