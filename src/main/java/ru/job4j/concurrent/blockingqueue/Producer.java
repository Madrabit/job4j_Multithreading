package ru.job4j.concurrent.blockingqueue;

/**
 * @author madrabit
 * Consumer is blocked if queue is full.
 */
public class Producer<T> {

    final SimpleBlockingQueue<T> queue;

    public Producer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public void offer(final T value) {
        Thread t = new Thread(() ->
                queue.offer(value));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
