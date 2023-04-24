package com.murmylo.volodymyr.locking;

public class InheritedLocking {

    synchronized void methodA() throws InterruptedException {
        System.out.println(this);
        System.out.println("method A called");
        Thread.sleep(3000);
    }

    static class Child extends InheritedLocking {
        synchronized void methodB() {
            System.out.println(this);
            System.out.println("method B called");
        }


        //demo to demonstrate that when you call parent's method, the lock will still be on child object
        public static void main(String[] args) throws InterruptedException {
            Child child = new Child();
            child.methodA();

            new Thread(child::methodB).start();
        }
    }

}
