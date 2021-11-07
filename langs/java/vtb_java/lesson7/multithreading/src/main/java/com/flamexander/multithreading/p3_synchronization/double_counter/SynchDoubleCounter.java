package com.flamexander.multithreading.p3_synchronization.double_counter;

public class SynchDoubleCounter {
    private long c1 = 0;
    private long c2 = 0;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public long value1() { return c1; }

    public long value2() { return c2; }

    public void inc1() {
        synchronized (lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized (lock2) {
            c2++;
        }
    }

    public void dec1() {
        synchronized (lock1) {
            c1--;
        }
    }

    public void dec2() {
        synchronized (lock2) {
            c2--;
        }
    }
}
