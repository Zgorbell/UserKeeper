package presenter;

import controller.Router;
import presenter.view.StartDialogMvpView;

public class StartDialogPresenter extends MvpPresenter<StartDialogMvpView> {

    public StartDialogPresenter(Router router){
        super(router);
    }

    @Override
    public void onViewStarted() {
        view.showChoices();
    }

    public void onCreateUserSelected(){
        router.startCreateUserDialog();
    }

    public void onShowAllUsersSelected(){
        router.startAllUsersDialog();
    }

    public void onCloseProgramSelected(){

    }

    public void onExitSelected(){

    }
}
