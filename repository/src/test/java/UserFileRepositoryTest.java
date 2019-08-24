import entity.*;
import error.PhoneValidationException;
import error.RoleValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.UserFileRepository;
import repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserFileRepositoryTest {

    private static final String DEFAULT_PATH = "users/";

    private static final String EVGENIY = "evgeniy";
    private static final String VASILIY = "vasiliy";
    private static final String ADOLF = "adolf";

    private UserRepository userRepository = new UserFileRepository(DEFAULT_PATH);

    @Before
    public void beforeTest() {
        try {
            Files.walk(Paths.get(DEFAULT_PATH))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {

                        }
                    });
        } catch (IOException e) {

        }
    }

    @Test
    public void test() {
        User user = getUser(EVGENIY);
        userRepository.save(user);
        UsersResult userResult = userRepository.getAllUsers();
        Assert.assertNotNull(userResult.getUsers());
        User savedUser = userResult.getUsers().get(0);
        userRepository.save(savedUser);
        Assert.assertEquals(savedUser.getPrimaryKey(), userRepository.getAllUsers().getUsers().get(0).getPrimaryKey());
    }

    private User getUser(String firstName) {
        Roles roles = new Roles();
        try {
            roles.add(new Role().setRole("role"));
        } catch (RoleValidationException e) {

        }
        Phones phones = new Phones();
        try {
            phones.add(new Phone().setPhone(3752982886979L));
        } catch (PhoneValidationException e) {

        }
        return new User(0, firstName, "User", "email", roles, phones);
    }

    @Test
    public void testDeleteUser() {
        User user = getUser(EVGENIY);
        userRepository.save(user);
        DeleteUserResult result = userRepository.deleteUser(user);
        Assert.assertEquals(result.getStatus(), DeleteUserResult.SUCCESS);
        DeleteUserResult resultAlreadyDeleted = userRepository.deleteUser(user);
        Assert.assertEquals(resultAlreadyDeleted.getStatus(), DeleteUserResult.ALREADY_DELETED);
        UsersResult usersResult = userRepository.getAllUsers();
        Assert.assertEquals(usersResult.getUsers().getSize(), 0);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = getUser(EVGENIY);
        User user2 = getUser(ADOLF);
        User user3 = getUser(VASILIY);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        UsersResult result = userRepository.getAllUsers();
        Assert.assertEquals(3, result.getUsers().getSize());
    }
}
