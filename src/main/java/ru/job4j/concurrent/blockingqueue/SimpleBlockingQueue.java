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
     * Items inside queue.
     */
    private int items = 0;


    /**
     * Queue as basic storage.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void offer(T value) {
        synchronized (monitor) {
            while (capacity < items) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            items++;
            queue.offer(value);
            monitor.notifyAll();
        }
    }

    public T poll() {
        synchronized (monitor) {
            while (queue.isEmpty()) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            items--;
            monitor.notifyAll();
            return queue.poll();

        }
    }
}
