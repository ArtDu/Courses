package com.geekbrains.lesson2;

public class Human implements RunAndJumpEntity {
    private int distLimit;
    private int jumpLimit;

    Human(int distLimit, int jumpLimit) {
        this.distLimit = distLimit;
        this.jumpLimit = jumpLimit;
    }

    public void run(int dist) {
        if (distLimit < dist)
            System.out.println("Human run");
        else
            System.out.println("Human exceed dist limit");
    }

    public void jump(int height) {
        if (jumpLimit < height)
            System.out.println("Human jump");
        else
            System.out.println("Human exceed jump limit");
    }
}
