package com.geekbrains.lesson1;

import java.util.HashMap;

public abstract class Animal {

    private static final HashMap<String, Integer> hm = new HashMap<String, Integer>();
    public int getCount(){
        return hm.get(getClassName());
    }

    Animal(String classname) {
        hm.put(classname, (hm.get(classname) == null ? 0 : hm.get(classname)) + 1);
    }

    protected abstract String getClassName();

    public abstract void run(int len);

    public abstract void swim(int len);
}
