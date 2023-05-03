package com.murmylo.volodymyr.exercices.diningphilosophers;

public class Philosopher extends Thread {
    private ChopStick left;
    private ChopStick right;

    public Philosopher(String name, ChopStick left, ChopStick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            eat();
        }
    }

    public void eat() {
        while (true) {
            boolean leftChopstick = left.getChopstick(this);
            boolean rightChopstick = right.getChopstick(this);
            if (leftChopstick && !rightChopstick) {
                left.releaseChopStick(this);
            }
            if (!leftChopstick && rightChopstick) {
                right.releaseChopStick(this);
            }
            if (leftChopstick && rightChopstick) {
                break;
            }
        }

        try {
            System.out.println(this.getName() + " has eaten");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        left.releaseChopStick(this);
        right.releaseChopStick(this);
    }

}
