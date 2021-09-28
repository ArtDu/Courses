package com.geekbrains.lesson2;

public class Barrier implements Executer {

    private final int height;

    Barrier(int height) {
        this.height = height;
    }

    public void execute(RunAndJumpEntity entity) {
        entity.jump(this.height);
    }

}
