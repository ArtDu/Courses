package geekbrains.vtb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhonebookImpl implements Phonebook {

    private final Map<String, List<String>> phones;

    public PhonebookImpl() {
        phones = new HashMap<>();
    }

    @Override
    public void add(String name, String phone) {
        List<String> listOfPhones = phones.get(name);
        if (listOfPhones == null) {
            phones.put(name, new ArrayList<>(Collections.singleton(phone)));
        } else {
            listOfPhones.add(phone);
        }
    }

    @Override
    public List<String> get(String name) {
        return phones.get(name);
    }
}


