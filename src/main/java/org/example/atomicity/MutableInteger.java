package org.example.atomicity;


import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe

/**
 *MutableInteger not thread-safe because the value field is
 *accessed from both get and set without synchronization. Among other hazards,
 *it is susceptible to stale values: if one thread calls set, other threads calling get
 *may or may not see that update
 *Thread 1 sets the new value and stores it in local cache instead of main memory
 *Thread 2 gets wrong value
 * Way to fix - lock on get/set
 */
public class MutableInteger {
    private volatile int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }


    public static void main(String[] args) throws InterruptedException {
        MutableInteger mutableInteger = new MutableInteger();
        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                int current = mutableInteger.get();
                mutableInteger.set(current + 1);
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        //we expect 2000, but due to race condition it won't be 200
        System.out.println(mutableInteger.get());
    }

}