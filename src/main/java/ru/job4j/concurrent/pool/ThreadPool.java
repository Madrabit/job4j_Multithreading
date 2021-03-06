package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author madrabit
 * Thread pool. Execute tasks added in Queue.
 * Size it is - Runtime.getRuntime().availableProcessors();
 */
public class ThreadPool {

    /**
     * Counter for waiting all Threads are finished.
     */
    private final CountDownLatch countDownLatch;

    /**
     * Work Threads list.
     */
    private final List<WorkThread> threads = new ArrayList<>();
    /**
     * My simple Blocking Queue/
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool(int size) {
        countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            threads.add(new WorkThread(tasks, countDownLatch));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * Offer job to task and execute it.
     *
     * @param job WorkThread.
     */
    public void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * Interrupt all threads.
     */
    public void shutdown() throws InterruptedException {
        countDownLatch.await();
        for (WorkThread thread : threads) {
            thread.stopThread();
        }
    }
}
