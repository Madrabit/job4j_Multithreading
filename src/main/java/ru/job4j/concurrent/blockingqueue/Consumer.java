package ru.job4j.concurrent.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author madrabit
 * Producer is blocked if queue empty.
 */
public class Consumer<T> {
    final SimpleBlockingQueue<T> queue;

    private final Queue<T> storage = new LinkedList<>();

    public Consumer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public Queue<T> getStorage() {
        return storage;
    }

    public void poll() {
        Thread t = new Thread(() -> {
            try {
                storage.offer(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
