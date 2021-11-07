package com.flamexander.multithreading.p7_concurrency;

import java.util.concurrent.ArrayBlockingQueue;

public class ProdConsExample {
    static class Producer {
        private ArrayBlockingQueue<String> queue;

        public Producer(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void put(String x) {
            try {
                System.out.println("Producer add: " + x);
                queue.put(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer {
        private ArrayBlockingQueue<String> queue;

        public Consumer(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        public String get() {
            try {
                String str = queue.take();
                System.out.println("Consumer get: " + str);
                return str;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        final ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(4);

        new Thread(() -> {
            Producer p = new Producer(arrayBlockingQueue);
            for (int i = 0; i < 10; i++) {
                try {
                    p.put(String.valueOf(i));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            Consumer c = new Consumer(arrayBlockingQueue);
            for (int i = 0; i < 10; i++) {
                try {
                    c.get();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}