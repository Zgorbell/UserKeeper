import basic.CommandDialog;
import basic.ExitDialog;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import objects.ChoicesMap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CommandDialogTest {

    private static final String CREATE_USER_MESSAGE = "Create a new user";
    private static final String SHOW_USERS_MESSAGE = "Show all users";
    private static final String CLOSE_PROGRAM_MESSAGE = "Close program";

    private static final int CREATE_USER_COMMAND = 0;
    private static final int SHOW_USERS_COMMAND = 1;
    private static final int CLOSE_PROGRAM_COMMAND = 2;

    private CommandDialog dialog;

    @Test
    public void test1(){
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(CREATE_USER_COMMAND).getBytes());
        dialog = new CommandDialog(inputStream);
        dialog.setChoicesMap(getMap());
        dialog.setListener(new CommandDialog.CommandDialogListener() {
            @Override
            public void onCommandSelected(String command) {
                Assert.assertEquals(command, CREATE_USER_MESSAGE);
            }

            @Override
            public void onExitEntered() {

            }
        });
        dialog.start();
    }

    @Test
    public void test2(){
        InputStream inputStream = new ByteArrayInputStream(ExitDialog.EXIT.getBytes());
        dialog = new CommandDialog(inputStream);
        CommandDialog.CommandDialogListener listener = Mockito.mock(CommandDialog.CommandDialogListener.class);
        dialog.setListener(listener);
        dialog.start();
        Mockito.verify(listener, Mockito.atLeastOnce()).onExitEntered();
    }

    private ChoicesMap getMap(){
        ChoicesMap choicesMap = new ChoicesMap();
        choicesMap.addChoice(CREATE_USER_COMMAND, CREATE_USER_MESSAGE);
        choicesMap.addChoice(SHOW_USERS_COMMAND, SHOW_USERS_MESSAGE);
        choicesMap.addChoice(CLOSE_PROGRAM_COMMAND, CLOSE_PROGRAM_MESSAGE);
        return choicesMap;
    }
}
