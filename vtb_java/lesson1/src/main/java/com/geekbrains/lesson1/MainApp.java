package com.geekbrains.lesson1;

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        Animal[] catAndDogs = {
                new Dog(),
                new Cat(),
                new Cat(),
        };
        Random rand = new Random(System.currentTimeMillis());
        int len;
        for(Animal o : catAndDogs) {
            len = rand.nextInt(500);
            o.run(len);
            o.swim(len);
            System.out.print("Count of " + o.getClassName() + "s: ");
            System.out.println(o.getCount());
        }
    }
}
