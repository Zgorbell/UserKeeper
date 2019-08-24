package basic;

import java.io.InputStream;

public class NumberDialog extends ExitDialog {

    private static final String INVALID_CHARACTERS = "You input invalid phone number. Try again";
    private NumberDialogListener listener;

    public NumberDialog(InputStream stream) {
        super(stream);
    }


    public void setListener(NumberDialogListener listener) {
        super.setListener(listener);
        this.listener = listener;
    }

    @Override
    protected void resolveInput() {
        super.resolveInput();
        try {
            listener.onNumberEntered(Long.parseLong(getInput()));
            setResolved();
        } catch (NumberFormatException e) {
            showNumberInvalidCharacters();
        }
    }

    @Override
    protected void doBeforeReadCommand() {

    }

    private void showNumberInvalidCharacters(){
        System.out.println(INVALID_CHARACTERS);
    }

    public interface NumberDialogListener extends ExitDialogListener{

        public void onNumberEntered(long number);
    }
}
