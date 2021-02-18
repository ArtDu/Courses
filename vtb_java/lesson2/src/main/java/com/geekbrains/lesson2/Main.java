package com.geekbrains.lesson2;

public class Main {
    public static void main(String[] args) {
        Executer[] executors = {
                new Barrier(5), new Treadmill(10),
        };

        RunAndJumpEntity[] runAndJumpEntities = {
                new Human(8,8)
        };

        for (RunAndJumpEntity entity : runAndJumpEntities){
            for (Executer executer : executors) {
                executer.execute(entity);
            }
        }
    }
}
