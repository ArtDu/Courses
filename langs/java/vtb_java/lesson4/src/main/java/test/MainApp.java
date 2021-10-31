package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApp {

    public static <T> void swap(List<T> arr, Integer lhs, Integer rhs) {
        if (lhs >= arr.size() || rhs >= arr.size()) {
            System.out.println("Nothing happened");
            return;
        }
        T tmp = arr.get(lhs);
        arr.set(lhs, arr.get(rhs));
        arr.set(rhs, tmp);
    }

    public static <T> ArrayList<T> toArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }

    public static void main(String[] args) {
        // 1
//        List<Integer> list = Arrays.asList(1,2,3,4);
//        swap(list, 0, 3);
//        System.out.println(list);

        // 2
//        Integer[] arr = {1,2,3,4};
//        System.out.println(toArrayList(arr));

        // 3
//        Box<Apple> apples = new Box<>();
//        Box<Orange> oranges = new Box<>();
//        apples.put(new Apple());
//        apples.put(new Apple());
//        apples.put(new Apple());
//        oranges.put(new Orange());
//        oranges.put(new Orange());
//        System.out.println(apples.getWeight());
//        System.out.println(oranges.getWeight());
//        System.out.println(apples.Compare(oranges));

        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        appleBox1.put(new Apple());

        appleBox2.put(new Apple());
        appleBox2.put(new Apple());
        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());

        appleBox1.swapBox(appleBox2);

        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());
    }
}
