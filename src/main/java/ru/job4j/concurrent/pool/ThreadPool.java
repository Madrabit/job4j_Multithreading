package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author madrabit
 * Thread pool. Execute tasks added in Queue.
 * Size it is - Runtime.getRuntime().availableProcessors();
 */
public class ThreadPool {

    /**
     * Work Threads list.
     */
    private List<WorkThread> threads = new LinkedList<>();
    /**
     * My simple Blocking Queue/
     */
    private SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool(int size) {
        for (int i = 0; i < size; i++) {
            threads.add(new WorkThread(tasks));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * Offer job to task and execute it.
     * @param job WorkThread.
     */
    public synchronized void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * Interrupt all threads.
     */
    public synchronized void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
