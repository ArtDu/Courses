package com.flamexander.multithreading.p2_race_condition;

public class SynchCounter {
    private int c;

    public int value() { return c; }

    public SynchCounter() {
        c = 0;
    }

    public synchronized void inc() {
        c++;
    }

    public synchronized void dec() {
        c--;
    }
}