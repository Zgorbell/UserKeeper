package basic;

import objects.ChoicesMap;

import java.io.InputStream;
import java.util.Map;

public class CommandDialog extends ExitDialog {

    private static final String MESSAGE = "Select an option";

    private static final int ERROR = -1;
    private int command = ERROR;
    private ChoicesMap choicesMap;
    private CommandDialogListener listener;

    public CommandDialog(InputStream inputStream) {
        super(inputStream);
        this.choicesMap = new ChoicesMap();
    }

    public void setListener(final CommandDialogListener listener) {
        super.setListener(listener);
        this.listener = listener;
    }

    public void setChoicesMap(ChoicesMap choicesMap) {
        this.choicesMap = choicesMap;
    }

    @Override
    protected void doBeforeReadCommand() {
        super.doBeforeReadCommand();
        System.out.println(MESSAGE);
        showChoices();
    }

    private void showChoices() {
        for (Map.Entry<Integer, String> entry : choicesMap.getEntrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


    @Override
    protected void resolveInput() {
        super.resolveInput();
        if(isResolved()) return;
        try {
            command = Integer.parseInt(getInput());
        } catch (NumberFormatException e) {
            command = ERROR;
        }
        resolveCommand();
    }

    private void resolveCommand() {
        if (command == ERROR && !isResolved()) System.out.println("Bad input");
        else if (!isCommandValid() && !isResolved()) System.out.println("Command not valid");
        else returnSelectedCommand();
    }

    private boolean isCommandValid() {
        return choicesMap.isCommandValid(command);
    }

    private void returnSelectedCommand() {
        listener.onCommandSelected(choicesMap.getCommand(command));
        setResolved();
    }

    public interface CommandDialogListener extends ExitDialogListener {

        void onCommandSelected(String command);
    }
}
