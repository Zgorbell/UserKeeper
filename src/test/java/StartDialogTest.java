import org.junit.Assert;
import org.junit.Test;
import dialog.StartDialog;
import org.mockito.Mockito;
import presenter.StartDialogPresenter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.nio.ByteBuffer;

public class StartDialogTest {

    private StartDialog dialog;

    @Test
    public void testCreateNewUser(){
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(StartDialog.CREATE_USER_COMMAND).getBytes());
        StartDialogPresenter presenter = Mockito.mock(StartDialogPresenter.class);
        presenter.setMvpView(dialog);
        dialog = new StartDialog(inputStream, presenter);
        dialog.start();
        Mockito.verify(presenter, Mockito.times(1)).onCreateUserSelected();
    }

    @Test
    public void testShowAllUsers(){
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(StartDialog.SHOW_USERS_COMMAND).getBytes());
        StartDialogPresenter presenter = Mockito.mock(StartDialogPresenter.class);
        dialog = new StartDialog(inputStream, presenter);
        dialog.start();
        Mockito.verify(presenter, Mockito.times(1)).onShowAllUsersSelected();
    }

    @Test
    public void testOnCloseProgramSelected(){
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(StartDialog.CLOSE_PROGRAM_COMMAND).getBytes());
        StartDialogPresenter presenter = Mockito.mock(StartDialogPresenter.class);
        dialog = new StartDialog(inputStream, presenter);
        dialog.start();
        Mockito.verify(presenter, Mockito.times(1)).onCloseProgramSelected();
    }

}
