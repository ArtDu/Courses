package com.flamexander.multithreading.p5_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceApp {
    public static void main(String[] args) {
        ExecutorService serv = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 12; i++) {
            String w = "#" + i;
            serv.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " - " + w + "-start");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + w + "-end");
                }
            });
        }
        serv.shutdown();
    }
}
