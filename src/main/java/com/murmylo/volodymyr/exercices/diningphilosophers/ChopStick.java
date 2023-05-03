package com.murmylo.volodymyr.exercices.diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
    private String name;

    public ChopStick(String name) {
        this.name = name;
    }

    private final Lock lock = new ReentrantLock();

    public boolean getChopstick(Philosopher philosopher) {
        System.out.println(philosopher.getName() + " tries acquiring " + this.name);
        try {
            return lock.tryLock(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {
        }
        return false;
    }

    public void releaseChopStick(Philosopher philosopher) {
        System.out.println(philosopher.getName() + " releasing " + this.name);
        lock.unlock();
    }
}
