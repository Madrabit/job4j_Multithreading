package ru.job4j.concurrent.blockingqueue;

/**
 * @author madrabit
 * Producer is blocked if queue empty.
 */
public class Producer<T> {
    final SimpleBlockingQueue<T> queue;

    public Producer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public void poll() {
        Thread t = new Thread(() -> System.out.println(queue.poll()));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
