
import org.junit.Test;
import dialog.CreateUserDialog;

import java.io.ByteArrayInputStream;

public class CreateUserDialgoTest {

    private CreateUserDialog dialog;

    @Test
    public void test(){
        StringBuilder builder = new StringBuilder();
        builder.append("Evgeniy")
                .append(System.lineSeparator())
                .append("Veremko")
                .append(System.lineSeparator())
                .append("dd@mail.ru");
//        dialog = new CreateUserDialog(new ByteArrayInputStream(builder.toString().getBytes()));
//        dialog.show();
    }
}
