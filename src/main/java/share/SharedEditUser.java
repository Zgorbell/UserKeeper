package share;

import lombok.Getter;
import lombok.Setter;
import objects.User;

public class SharedEditUser {

    @Getter
    @Setter
    private User user;

    private static volatile SharedEditUser instance;

    public static SharedEditUser getInstance() {
        SharedEditUser localInstance = instance;
        if (localInstance == null) {
            synchronized (SharedEditUser.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SharedEditUser();
                }
            }
        }
        return localInstance;
    }
}
