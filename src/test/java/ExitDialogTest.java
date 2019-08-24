import basic.ExitDialog;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ExitDialogTest {

    private ExitDialog dialog;

    @Test
    public void test(){
        InputStream inputStream = new ByteArrayInputStream(ExitDialog.EXIT.getBytes());
        dialog = new ExitDialog(inputStream);
        ExitDialog.ExitDialogListener listener = Mockito.mock(ExitDialog.ExitDialogListener.class);
        dialog.setListener(listener);
        dialog.start();
        Mockito.verify(listener, Mockito.times(1)).onExitEntered();
    }
}
