package test;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private final List<T> fruits;

    {
        fruits = new ArrayList<>();
    }

    public void put(T fruit) {
        fruits.add(fruit);
    }

    public Double getWeight() {
        if(fruits.isEmpty()) return 0.0;
        return fruits.size() * fruits.get(0).getWeight();
    }

    public boolean Compare(Box<?> rhs) {
        return getWeight().equals(rhs.getWeight());
    }

    public void swapBox(Box<T> rhs) {
        List<T> tmp = new ArrayList<>(fruits);
        fruits.clear();
        fruits.addAll(rhs.fruits);
        rhs.fruits.clear();
        rhs.fruits.addAll(tmp);
    }
}
