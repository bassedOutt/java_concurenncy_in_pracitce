package com.murmylo.volodymyr.locking;

public class NonReentrantBlockDeadLock {
    public class Widget {
        public synchronized void doSomething() {
        }
    }


    // if we used non-intrinsic lock we'd get deadlock here
    public class LoggingWidget extends Widget {
        public synchronized void doSomething() {
            System.out.println(toString() + ": calling doSomething");
            super.doSomething();
        }
    }

}
