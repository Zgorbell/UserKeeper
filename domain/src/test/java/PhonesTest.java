import entity.Phone;
import entity.Phones;
import error.PhoneValidationException;
import org.junit.Assert;
import org.junit.Test;

public class PhonesTest {

    public static final long TEST_PHONE_1 = 375298288697L;

    private Phones phones;

    @Test
    public void test(){
        phones = new Phones();
        try {
            phones.add(new Phone().setPhone(TEST_PHONE_1));
        }catch (PhoneValidationException e){

        }
        Assert.assertEquals(phones.getPhones().size(), 1);
        Assert.assertEquals(phones.getPhones().get(0).getPhone(), TEST_PHONE_1);
    }
}
