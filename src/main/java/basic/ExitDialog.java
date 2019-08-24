package basic;

import java.io.InputStream;
import java.util.Scanner;

public class ExitDialog {

    public static final String EXIT = "exit";
    private static final String MESSAGE = "Enter exit to close dialog";
    private static boolean isExitShown = false;

    private Scanner scanner;
    private String input;
    private ExitDialogListener listener;
    private StringListener stringListener;
    private boolean resolved;

    public ExitDialog(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        input = "";
    }

    public void setListener(ExitDialogListener listener) {
        this.listener = listener;
    }

    public void setStringListener(StringListener listener){
        this.stringListener = listener;
    }

    public void start(){
        do {
            doBeforeReadCommand();
            readCommand();
            resolveInput();
        }
        while (!isResolved());
    }

    protected void doBeforeReadCommand(){
        showExitMessage();
    }

    private void showExitMessage(){
        if(!isExitShown) {
            System.out.println(MESSAGE);
            isExitShown = true;
        }
    }

    private void readCommand() {
        input = scanner.nextLine();
    }

    protected void resolveInput() {
        if (EXIT.equals(input.toLowerCase())) {
            onExitEntered();
            setResolved();
            return;
        }
        if(stringListener != null){
            stringListener.onStringEntered(input);
            setResolved();
        }
    }

    protected void onExitEntered(){
        if(listener != null) listener.onExitEntered();
    }

    protected boolean isResolved(){
        return resolved;
    }

    protected void setResolved(){
        resolved = true;
    }

    protected String getInput(){
        return input;
    }


    public interface ExitDialogListener {

        void onExitEntered();
    }

    public interface StringListener {

        void onStringEntered(String input);
    }
}
