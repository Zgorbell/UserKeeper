import entity.Role;
import entity.Roles;
import error.RoleValidationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RolesTest {

    private static final String ROLE1 = "ROLE1";
    private static final String ROLE2 = "ROLE2";
    private static final String ROLE3 = "ROLE3";
    private static final String ROLE4 = "ROLE4";

    private Roles roles;

    @Test
    public void test(){
        try {
            roles = new Roles();
            roles.add(new Role().setRole(ROLE1));
        }catch (RoleValidationException e){
            Assert.fail();
        }
    }

    @Test
    public void test2(){
        try {
            roles = new Roles();
            roles.add(new Role().setRole(ROLE1));
            roles.set(new Role().setRole(ROLE2), 1);
            roles.add(new Role().setRole(ROLE1));
            Assert.fail();
        }catch (RoleValidationException e){
            if(e.getType() != RoleValidationException.Type.ROLE_ALREADY_EXIST) Assert.fail();
        }
    }

    @Test
    public void test3(){
        try{
            roles = new Roles();
            roles.add(new Role().setRole(ROLE1));
            roles.set(new Role().setRole(ROLE2), 1);
            roles.add(new Role().setRole(ROLE3));
            roles.add(new Role().setRole(ROLE4));
            Assert.fail();
        } catch (RoleValidationException e){
            if(e.getType() != RoleValidationException.Type.ROLE_COUNT_ERROR) Assert.fail();
        }
    }
}
