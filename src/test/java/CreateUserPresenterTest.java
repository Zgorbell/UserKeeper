import controller.Router;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import presenter.CreateUserPresenter;
import presenter.view.CreateUserMvpView;
import repository.UserRepository;

public class CreateUserPresenterTest {

    private static final String FIRST_NAME = "Evgeniy";
    private static final String SECOND_NAME = "Banah";
    private static final String EMAIL = "email@mail.ru";
    private static final long PHONE1 = 375298243697L;
    private static final long PHONE2 = 375291234354L;
    private static final String ROLE1 = "role1";
    private static final String ROLE2 = "role2";

    private CreateUserPresenter presenter;
    private CreateUserMvpView view;
    private Router router;
    private UserRepository userRepository;


    @Before
    public void prepare(){
        view = Mockito.mock(CreateUserMvpView.class);
        router = Mockito.mock(Router.class);
        userRepository = Mockito.mock(UserRepository.class);
        presenter = new CreateUserPresenter(router, userRepository);
        presenter.setMvpView(view);
    }

    @Test
    public void test1(){
        presenter.onViewStarted();
        Mockito.verify(view, Mockito.times(1)).showMessageRequestFirstName();
        Mockito.verify(view, Mockito.times(1)).requestFirstName();
        presenter.onFirstNameEntered(FIRST_NAME);
        Mockito.verify(view, Mockito.times(1)).showMessageRequestSecondName();
        Mockito.verify(view, Mockito.times(1)).requestSecondName();
        presenter.onSecondNameEntered(SECOND_NAME);
        Mockito.verify(view, Mockito.times(1)).showMessageRequestEmail();
        Mockito.verify(view, Mockito.times(1)).requestEmail();
        presenter.onEmailEntered(EMAIL);
        Mockito.verify(view, Mockito.times(1)).showMessageRequestPhone();
        Mockito.verify(view, Mockito.times(1)).requestPhone();
        presenter.onPhoneEntered(PHONE1);
        Mockito.verify(view, Mockito.times(1)).askMoreOnePhone();
        presenter.onMoreOnePhoneEnteredPositive();
        Mockito.verify(view, Mockito.times(2)).showMessageRequestPhone();
        Mockito.verify(view, Mockito.times(2)).requestPhone();
        presenter.onPhoneEntered(PHONE2);
        Mockito.verify(view, Mockito.times(2)).askMoreOnePhone();
        presenter.onMoreOneRoleEnteredPositive();
        Mockito.verify(view, Mockito.times(1)).showMessageRequestRole();
        Mockito.verify(view, Mockito.times(1)).requestRole();
        presenter.onRoleEntered(ROLE1);
    }
}
