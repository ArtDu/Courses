package com.flamexander.multithreading.p6_locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLockApp {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.err.println("T-BEFORE-LOCK-FIRST");
                    lock.lock();
                    System.err.println("T-GET-LOCK-FIRST");
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    System.err.println("T-END-FIRST");
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("T-BEGIN-SECOND");
                try {
                    if (lock.tryLock(5, TimeUnit.SECONDS)) {
                        try {
                            System.err.println("T-LOCK-SECTION-SECOND");
                            try {
                                Thread.sleep(13000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } finally {
                            lock.unlock();
                            System.err.println("T-END-SECOND");
                        }
                    } else {
                        System.err.println("Не очень-то и нужно было...");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
