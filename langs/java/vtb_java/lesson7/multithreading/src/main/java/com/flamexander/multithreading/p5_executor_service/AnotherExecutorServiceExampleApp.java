package com.flamexander.multithreading.p5_executor_service;

import java.util.concurrent.*;

public class AnotherExecutorServiceExampleApp {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "Java";
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("END");
        service.shutdown();
    }
}
