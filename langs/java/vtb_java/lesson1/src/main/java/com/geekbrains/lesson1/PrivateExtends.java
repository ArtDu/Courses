package com.geekbrains.lesson1;

public class PrivateExtends {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        tiger.setName("tigr");
        System.out.println(tiger.getName());
        // will be compile error
//        System.out.println(tiger.name);
    }
}
