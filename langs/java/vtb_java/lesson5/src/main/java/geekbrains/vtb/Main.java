package geekbrains.vtb;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1
        String[] arr = new String[]{"a", "a", "b"};
        Map<String, Integer> hm = new HashMap<>();
        for (String o :
                arr) {
            hm.merge(o, 1, Integer::sum);
        }
        System.out.println(hm.size());
        System.out.println(hm) ;


        // 2
        Phonebook phonebook = new PhonebookImpl();
        phonebook.add("Dub", "123");
        phonebook.add("Dub", "12345");
        phonebook.add("Art", "+7904");
        System.out.println(phonebook.get("Dub"));
        System.out.println(phonebook.get("Art"));
    }
}
