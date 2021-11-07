package vtb.geekbrains;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore smp;

    public Tunnel() {
        this.smp = new Semaphore(Main.CARS_COUNT / 2);

        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            if (!smp.tryAcquire()) {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
            }
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            smp.release();
        }
    }
}
