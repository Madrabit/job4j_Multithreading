package ru.job4j.concurrent.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author madrabit
 * Concurrent queue.
 */

@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * Object monitor.
     */
    private final Object monitor = this;

    /**
     * Queue size.
     */
    private final int capacity;

    /**
     * Queue as basic storage.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void offer(T value) {
        synchronized (monitor) {
            while (capacity <= queue.size()) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(value);
            monitor.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (monitor) {
            while (queue.isEmpty()) {
                monitor.wait();
            }
            T result = queue.poll();
            monitor.notifyAll();
            return result;

        }
    }
}
