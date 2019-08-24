import entity.Phone;
import error.PhoneValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PhoneTest {


    private static final long VALID_PHONE = 375298288697L;
    private static final long NO_VALID_PHONE = 1375298288697L;
    private Phone phone;

    @Test()
    public void testValid(){
        phone = new Phone();
        try{
            phone.setPhone(VALID_PHONE);
            Assert.assertEquals(phone.getPhone(), VALID_PHONE);
        } catch (PhoneValidationException e){
            Assert.fail();
        }
    }

    @Test()
    public void testNonValid(){
        phone = new Phone();
        try{
            phone.setPhone(NO_VALID_PHONE);
            Assert.assertEquals(phone.getPhone(), VALID_PHONE);
            Assert.fail();
        } catch (PhoneValidationException e){

        }
    }
}
