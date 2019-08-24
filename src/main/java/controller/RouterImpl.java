package controller;

import dialog.DialogFactory;

public class RouterImpl implements Router {

    private DialogFactory dialogFactory;

    public RouterImpl(DialogFactory dialogFactory){
        this.dialogFactory = dialogFactory;
    }

    @Override
    public void startStartDialog() {
        new Thread(() -> dialogFactory.getStartDialog().start()).start();
    }

    @Override
    public void startAllUsersDialog() {
        new Thread(() -> dialogFactory.getAllUsers().start()).start();
    }

    @Override
    public void startUserDialog() {
        new Thread(() -> dialogFactory.getUserDialog().start()).start();
    }

    @Override
    public void startCreateUserDialog() {
        new Thread(() -> dialogFactory.getCreateUserDialog().start()).start();
    }

    @Override
    public void startEditUserDialog() {
        new Thread(() -> dialogFactory.getEditUserDialog().start()).start();
    }

    @Override
    public void startEditFirstNameDialog() {
        new Thread(() -> dialogFactory.getEditUserFirstNameDialog().start()).start();
    }

    @Override
    public void startEditSecondNameDialog() {
        new Thread(() -> dialogFactory.getEditUserSecondNameDialog().start()).start();
    }

    @Override
    public void startEditEmailDialog() {
        new Thread(() -> dialogFactory.getEditUserEmailDialog().start()).start();
    }

    @Override
    public void startEditPhoneDialog() {
        new Thread(() -> dialogFactory.getEditUserPhonesDialog().start()).start();
    }

    @Override
    public void startEditRolesDialog() {
        new Thread(() -> dialogFactory.getEditUserRolesDialog().start()).start();
    }
}
