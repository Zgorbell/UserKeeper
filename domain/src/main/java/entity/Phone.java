package entity;

import error.PhoneValidationException;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class Phone extends Object {

    private static final double HIGH_LIMIT = Math.pow(10, 12);
    private static final double LOW_LIMIT = Math.pow(10, 11);

    @Getter
    private long phone;

    public Phone() {
    }

    public boolean equals(Phone phone) {
        return this.phone == phone.phone;
    }

    public Phone setPhone(long phone) throws PhoneValidationException {
        if (phone - HIGH_LIMIT > 0) throw PhoneValidationException.getPhoneInvalidException();
        if (phone - LOW_LIMIT < 0) throw PhoneValidationException.getPhoneInvalidException();
        this.phone = phone;
        return this;
    }
}
