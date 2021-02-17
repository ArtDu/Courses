package com.geekbrains.lesson1;

public class Cat extends Animal {

    Cat() {
        super("Cat");
    }

    @Override
    protected String getClassName() {
        return "Cat";
    }

    @Override
    public void run(int len) {
        if(len < 200) {
            System.out.println("cat run " + len + " meters");
        } else {
            System.out.println("cat exceed limit: " + 200 + " meters, current len: " + len);
        }
    }

    @Override
    public void swim(int len) {
        System.out.println("cat doesn't swim");
    }


}
