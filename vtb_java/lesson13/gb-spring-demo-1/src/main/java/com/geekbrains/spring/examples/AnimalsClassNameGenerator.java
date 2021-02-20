package com.geekbrains.spring.examples;

public class AnimalsClassNameGenerator implements ClassNameGenerator {
    public String getRandomClassName() {
        String[] names = {"Cat", "Dog", "Cow", "Horse"};
        return names[(int) (Math.random() * names.length)];
    }
}
