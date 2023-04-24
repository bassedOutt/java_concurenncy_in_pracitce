package com.murmylo.volodymyr.atomicity;

public class NoVisibility {
    private static boolean ready;
    private static int number;

    /**
     * can print 0 or not terminate at all.
     * why? the ready flag may have been modified in Main thread cache memory
     * since java does not guarantee that those changes will be flushed back to main memory
     * it is possible that it will never be flushed to main memory
     * why it can print 0? reordering
     */
    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        number = 42;
        Thread.sleep(1111);
        ready = true;
    }
}