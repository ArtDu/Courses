package com.flamexander.multithreading.p7_concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchApp {
    public static void main(String[] args) {
        final int THREADS_COUNT = 6;

        final CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

        System.out.println("Начинаем");
        for (int i = 0; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    Thread.sleep(200 * w + (int)(500 * Math.random()));
                    countDownLatch.countDown();
                    System.out.println("Поток #" + w + " - готов");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Работа завершена");
    }
}
