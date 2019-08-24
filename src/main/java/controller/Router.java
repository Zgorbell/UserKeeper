package controller;

import entity.User;

public interface Router {

    void startStartDialog();

    void startAllUsersDialog();

    void startUserDialog();

    void startCreateUserDialog();

    void startEditUserDialog();

    void startEditFirstNameDialog();

    void startEditSecondNameDialog();

    void startEditEmailDialog();

    void startEditPhoneDialog();

    void startEditRolesDialog();
}
