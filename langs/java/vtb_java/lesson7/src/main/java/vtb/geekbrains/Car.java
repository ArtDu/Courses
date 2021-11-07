package vtb.geekbrains;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private final CountDownLatch cdl;
    private final CyclicBarrier cb;
    private static Boolean winnerFinished = false;
    private static final Object lock = new Object();

    private final Race race;
    private final int speed;
    private final String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch cdl, CyclicBarrier cb) {
        this.cdl = cdl;
        this.cb = cb;

        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cdl.countDown();
        try {
            cb.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            if(!winnerFinished) {
                synchronized (lock) {
                    if(!winnerFinished) {
                        winnerFinished = true;
                        System.out.println(this.name + " - WIN");
                    }
                }
            }
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
