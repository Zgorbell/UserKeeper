package dialog;

import entity.User;

public interface DialogFactory {

    CreateUserDialog getCreateUserDialog();

    StartDialog getStartDialog();

    AllUsersDialog getAllUsers();

    UserDialog getUserDialog();

    EditUserDialog getEditUserDialog();

    EditUserNameDialog getEditUserFirstNameDialog();

    EditUserNameDialog getEditUserSecondNameDialog();

    EditUserPhonesDialog getEditUserPhonesDialog();

    EditUserEmailDialog getEditUserEmailDialog();

    EditUserRolesDialog getEditUserRolesDialog();


}
