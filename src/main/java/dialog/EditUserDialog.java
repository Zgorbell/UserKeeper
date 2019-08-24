package dialog;

import basic.CommandDialog;
import basic.Dialog;
import entity.Phone;
import entity.Role;
import objects.User;
import objects.ChoicesMap;
import presenter.EditUserPresenter;
import presenter.view.EditUserMvpView;

import java.io.InputStream;

public class EditUserDialog implements Dialog, EditUserMvpView {

    private static final String FIRST_NAME_COMMAND = "Edit first name";
    private static final String SECOND_NAME_COMMAND = "Edit second name";
    private static final String EMAIL_COMMAND = "Edit email";
    private static final String ROLE_COMMAND = "Edit roles";
    private static final String PHONES_COMMAND = "Edit phones";
    private static final String SAVE_USER = "Save user";

    private EditUserPresenter presenter;
    private CommandDialog dialog;

    public EditUserDialog(InputStream inputStream, EditUserPresenter presenter) {
        this.presenter = presenter;
        dialog = new CommandDialog(inputStream);
    }

    @Override
    public void start() {
        presenter.onViewStarted();
    }

    @Override
    public void showUserDetails(User user) {
        System.out.println("First name: " + user.getFirstName());
        System.out.println("Second name: " + user.getSecondName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phones: ");
        for (Phone phone : user.getPhones()) {
            System.out.println(phone.getPhone());
        }
        System.out.println("Roles: ");
        for (Role role : user.getRoles()) {
            System.out.println(role.getRole());
        }
    }

    @Override
    public void showEditVariants() {
        setDialogListener();
        setDialogChoices();
        dialog.start();
    }

    @Override
    public void requestNewFirsName() {

    }

    @Override
    public void requestNewSecondName() {

    }

    @Override
    public void requestNewEmail() {

    }

    private void setDialogChoices(){
        ChoicesMap map = new ChoicesMap();
        map.addChoice(0, FIRST_NAME_COMMAND);
        map.addChoice(1, SECOND_NAME_COMMAND);
        map.addChoice(2, EMAIL_COMMAND);
        map.addChoice(3, ROLE_COMMAND);
        map.addChoice(4, PHONES_COMMAND);
        map.addChoice(5, SAVE_USER);
        dialog.setChoicesMap(map);
    }

    private void setDialogListener() {
        dialog.setListener(new CommandDialog.CommandDialogListener() {
            @Override
            public void onCommandSelected(String command) {
                switch (command) {
                    case FIRST_NAME_COMMAND:
                        presenter.onFirstNameEditSelected();
                        break;
                    case SECOND_NAME_COMMAND:
                        presenter.onSecondNameEditSelected();
                        break;
                    case EMAIL_COMMAND:
                        presenter.onEmailEditSelected();
                        break;
                    case ROLE_COMMAND:
                        presenter.onRoleEditSelected();
                        break;
                    case PHONES_COMMAND:
                        presenter.onPhoneEditSelected();
                        break;
                    case SAVE_USER:
                        presenter.onSaveUserSelected();
                        break;
                }
            }

            @Override
            public void onExitEntered() {
                presenter.onExitSelected();
            }
        });
    }
}
