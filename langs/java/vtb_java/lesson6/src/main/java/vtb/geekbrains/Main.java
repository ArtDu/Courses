package vtb.geekbrains;

public class Main {
    static float[] calc() {
        final int SIZE = 10_000_000;
        final int HALF = SIZE / 2;
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long end = System.currentTimeMillis();
        System.out.println("Single thread:");
        System.out.println(end - begin);
        return arr;
    }


    public static class CalculateSubarray implements Runnable {
        private float[] arr;

        public CalculateSubarray(float[] arr) {
            this.arr = arr;
        }

        public void run() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }

    static float[] calcWithTwoThreads() throws InterruptedException {
        final int SIZE = 10_000_000;
        final int HALF = SIZE / 2;
        float[] arr = new float[SIZE];
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long begin = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);
        Thread firstHalf = new Thread( () -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        } );
        Thread secondHalf = new Thread( () -> {
            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) * Math.cos(0.4f + (i + HALF) / 2));
            }
        } );
        firstHalf.start();
        secondHalf.start();

        firstHalf.join();
        secondHalf.join();

        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);

        long end = System.currentTimeMillis();
        System.out.println("Two threads:");
        System.out.println(end - begin);
        return arr;
    }

    public static void main(String[] args) throws InterruptedException {
        float[] res1 = calc();
        float[] res2 = calcWithTwoThreads();
        for (int i = 0; i < res1.length; i++) {
            if(res1[i] != res2[i]) {
                System.out.println("Error");
                break;
            }
        }
    }
}
