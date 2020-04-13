package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;

/**
 * @author madrabit
 * Thread for pool.
 */
public class WorkThread extends Thread {
    /**
     * Tasks for execution.
     */
    private final SimpleBlockingQueue tasks;

    public WorkThread(SimpleBlockingQueue queue) {
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
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
