package com.geekbrains.lesson1;

public class Dog extends Animal {


    Dog() {
        super("Dog");
    }

    @Override
    protected String getClassName() {
        return "Dog";
    }

    @Override
    public void run(int len) {
        System.out.println("dog run " + String.valueOf(len) + " meters");
    }

    @Override
    public void swim(int len) {
        System.out.println("dog swim " + String.valueOf(len) + " meters");
    }
}
