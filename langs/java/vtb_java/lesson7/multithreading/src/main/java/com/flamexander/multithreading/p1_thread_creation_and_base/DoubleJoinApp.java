package com.flamexander.multithreading.p1_thread_creation_and_base;

public class DoubleJoinApp {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.err.println("t1-" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.err.println("t2-" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            System.err.println("1");
            t1.join();
            System.err.println("2");
            t2.join();
            System.err.println("3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
