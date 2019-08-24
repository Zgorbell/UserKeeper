package presenter;

import controller.Router;
import presenter.view.MvpView;
import presenter.view.UserMvpView;

abstract class  MvpPresenter<T extends MvpView> {

    protected final Router router;
    protected T view;

    public MvpPresenter(final Router router){
        this.router = router;
    }

    public void setMvpView(T userMvpView){
        this.view = userMvpView;
    }

    abstract public void onViewStarted();

    public void onExitEntered(){

    }
}
