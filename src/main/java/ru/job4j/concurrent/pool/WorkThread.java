package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;

import java.util.concurrent.CountDownLatch;

/**
 * @author madrabit
 * Thread for pool.
 */
public class WorkThread<T, E> extends Thread {

    /**
     * For count down Latch.
     */
    private final CountDownLatch countDownLatch;
    /**
     * Tasks for execution.
     */
    private final SimpleBlockingQueue<T> tasks;

    public WorkThread(SimpleBlockingQueue<T> queue, CountDownLatch latch) {
        countDownLatch = latch;
        tasks = queue;
    }

    /**
     * Getting task from queue and execute it.
     */
    public void run() {
        while (!isInterrupted()) {
            try {
                Runnable runnable = (Runnable) tasks.poll();
                runnable.run();
                countDownLatch.countDown();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Stop the thread form outside.
     */
    public void stopThread() {
        this.isInterrupted();
    }
}
