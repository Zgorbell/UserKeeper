package entity;

import error.RoleValidationException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Roles {

    @Getter
    private List<Role> roles;

    public Roles() {
        roles = new ArrayList<>();
    }

    public int size() {
        return roles.size();
    }

    public void add(Role role) throws RoleValidationException {
        if (roles.size() >= 3) throw RoleValidationException.getRoleCountLimitException();
        if (checkIsExit(role)) throw RoleValidationException.getRoleAlreadyExistException();
        roles.add(role);
    }

    public void set(Role role, int position) throws RoleValidationException {
        if(roles.size() - position <= 0) add(role);
        else roles.set(position, role);
    }

    public List<String> getRolesStrings() {
        List<String> phoneList = new ArrayList<>();
        for (Role r : roles) {
            phoneList.add(String.valueOf(r.getRole()));
        }
        return phoneList;
    }

    private boolean checkIsExit(Role role) {
        for (Role r : roles) {
            if (r.equals(role)) return true;
        }
        return false;
    }
}
