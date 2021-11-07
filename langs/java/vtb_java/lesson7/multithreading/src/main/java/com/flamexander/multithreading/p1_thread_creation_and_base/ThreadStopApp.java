package com.flamexander.multithreading.p1_thread_creation_and_base;

public class ThreadStopApp {
    public static void main(String[] args) {
        correct();
    }

    public static void correct() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean inter = false;
                while (true) {
                    if (Thread.currentThread().isInterrupted() || inter) {
                        break;
                    }
                    System.out.println("tick");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        inter = true;
                    }
                }
            }
        });
        t.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }

    public static void badIdea() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("tick");
                }
            }
        });
        t.start();

        try {
            Thread.sleep(3000);
            t.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
