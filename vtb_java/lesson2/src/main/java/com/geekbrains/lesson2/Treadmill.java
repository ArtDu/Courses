package com.geekbrains.lesson2;

public class Treadmill implements Executer {

    private final int dist;

    Treadmill(int dist) {
        this.dist = dist;
    }

    public void execute(RunAndJumpEntity entity) {
        entity.run(this.dist);
    }
}
