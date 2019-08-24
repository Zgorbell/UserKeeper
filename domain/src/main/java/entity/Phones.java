package entity;

import error.PhoneValidationException;
import lombok.Getter;

import java.util.*;

public class Phones {

    @Getter
    private List<Phone> phones;

    public Phones() {
        phones = new ArrayList<>(3);
    }

    public int size() {
        return phones.size();
    }

    public void add(Phone phone) throws PhoneValidationException {
        if (phones.size() >= 3) throw PhoneValidationException.getPhonesCountLimitException();
        if (checkIsExit(phone)) throw PhoneValidationException.getphoneAlreadyExistException();
        phones.add(phone);
    }

    public void set(Phone phone, int position) {
        phones.add(position, phone);
    }

    public List<String> getPhonesStrings() {
        List<String> phoneList = new ArrayList<>();
        for (Phone phone : phones) {
            phoneList.add(String.valueOf(phone.getPhone()));
        }
        return phoneList;
    }

    private boolean checkIsExit(Phone newPhone) {
        for (Phone phone : phones) {
            if (phone.equals(newPhone)) return true;
        }
        return false;
    }

}
