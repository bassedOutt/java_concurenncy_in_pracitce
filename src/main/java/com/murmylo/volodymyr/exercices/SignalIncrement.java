package com.murmylo.volodymyr.exercices;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class SignalIncrement {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                counter.increment();
            }
            lock.lock();
            condition.signal();
            lock.unlock();
        });
        Thread printThread = new Thread(() -> {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Counter value: " + counter.getValue());
            lock.unlock();
        });
        incrementThread.start();
        printThread.start();
    }

    static class Counter {
        private int value = 0;

        public void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }
    }
}

