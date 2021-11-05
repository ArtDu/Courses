package geekbrains.vtb;

import java.util.List;

public interface Phonebook {
    public void add(String name, String phone);
    public List<String> get(String name);
}
