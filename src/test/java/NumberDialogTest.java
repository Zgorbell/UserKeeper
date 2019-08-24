import basic.ExitDialog;
import basic.NumberDialog;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class NumberDialogTest {

    private static final long TEST_NUMBER  = 12345678;
    private NumberDialog dialog;

    @Test
    public void test(){
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(TEST_NUMBER).getBytes());
        dialog = new NumberDialog(inputStream);
        NumberDialog.NumberDialogListener listener = Mockito.mock(NumberDialog.NumberDialogListener.class);
        dialog.setListener(listener);
        dialog.start();
        Mockito.verify(listener, Mockito.times(1)).onNumberEntered(TEST_NUMBER);
    }
}
