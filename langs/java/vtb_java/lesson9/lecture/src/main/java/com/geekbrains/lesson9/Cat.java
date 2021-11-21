package com.geekbrains.lesson9;

public class Cat {
    private int privateField;
    int defaultField;
    public int publicField;

    public Cat(int privateField, int defaultField, int publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.publicField = publicField;
    }

    public Cat() {
    }

    public final void publicMeow() {
        System.out.println("public meow");
    }

    private void privateMeow() {
        System.out.println("private meow");
    }
}
