package presenter;

import controller.Router;
import entity.Users;
import entity.UsersResult;
import objects.User;
import presenter.view.AllUsersMvpView;
import repository.UserRepository;
import share.SharedEditUser;

public class AllUsersDialogPresenter extends MvpPresenter<AllUsersMvpView>{

    private UserRepository userRepository;
    private Users users;

    public AllUsersDialogPresenter(UserRepository userRepository, Router router) {
        super(router);
        this.userRepository = userRepository;
    }

    @Override
    public void onViewStarted(){
        UsersResult usersResult = userRepository.getAllUsers();
        users = usersResult.getUsers();
        if (users != null && users.getSize() != 0) {
            view.showMessageRequestCommand();
            view.showAllUsers(users);
        } else if (users != null) {
            view.showMessageUsersNotRegistered();
            view.askAnyKeyToExit();
        } else {
            view.showErrorReadUser();
        }
        view.requestCommand();
    }

    public void onUserSelected(int number){
        Users.SearchArguments.SearchArgumentsBuilder builder = Users.SearchArguments.builder();
        builder.number(number);
        findUser(builder.build());
    }

    public void onUserSelected(String name){
        Users.SearchArguments.SearchArgumentsBuilder builder = Users.SearchArguments.builder();
        builder.fullName(name);
        findUser(builder.build());
    }

    public void onExitEntered(){
        router.startStartDialog();
    }

    private void findUser(Users.SearchArguments arguments){
        try{
            SharedEditUser.getInstance().setUser(new User(users.findUser(arguments)));
            router.startUserDialog();
        } catch (Users.UserNotFound e) {
            System.out.println("User not found, try again");
            view.requestCommand();
        }

    }
}
