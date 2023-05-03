package com.murmylo.volodymyr.exercices.diningphilosophers;

public class Main {

    public static void main(String[] args) {
        ChopStick chopStick1 = new ChopStick("chopstick1");
        ChopStick chopStick2 = new ChopStick("chopstick2");
        ChopStick chopStick3 = new ChopStick("chopstick3");
        ChopStick chopStick4 = new ChopStick("chopstick4");
        ChopStick chopStick5 = new ChopStick("chopstick5");

        Philosopher philosopher1 = new Philosopher("philospher1", chopStick1, chopStick2);
        Philosopher philosopher2 = new Philosopher("philospher2", chopStick2, chopStick3);
        Philosopher philosopher3 = new Philosopher("philospher3", chopStick3, chopStick4);
        Philosopher philosopher4 = new Philosopher("philospher4", chopStick4, chopStick5);
        Philosopher philosopher5 = new Philosopher("philospher5", chopStick5, chopStick1);

        Philosopher[] philosophers = new Philosopher[]{philosopher1, philosopher2, philosopher3, philosopher4, philosopher5};

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }
}
