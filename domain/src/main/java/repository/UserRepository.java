package repository;

import entity.DeleteUserResult;
import entity.User;
import entity.UserResult;
import entity.UsersResult;

public interface UserRepository {

    void save(User user);

    UsersResult getAllUsers();

    DeleteUserResult deleteUser(User user);
}
