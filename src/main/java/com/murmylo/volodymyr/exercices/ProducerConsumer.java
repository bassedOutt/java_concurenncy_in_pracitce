package com.murmylo.volodymyr.exercices;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private final Queue<Integer> queue;
    private final int capacity;

    public ProducerConsumer(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public void produce(int item) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == capacity) {
                wait();
            }
            queue.add(item);
            notifyAll();
        }
    }

    public int consume() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                wait();
            }
            int item = queue.remove();
            notifyAll();
            return item;
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(5);

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pc.produce(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.start();

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumerThread.start();
    }
}

