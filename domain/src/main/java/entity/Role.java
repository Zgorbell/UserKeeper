package entity;

import error.RoleValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


public class Role {

    @Getter
    private String role;

    public Role setRole(String role) throws RoleValidationException
    {
        if(role == null || role.isEmpty()) throw RoleValidationException.getRoleIsEmptyException();
        this.role = role;
        return this;
    }

    public boolean equals(Role role) {
        return this.role.equals(role.getRole());
    }
}
