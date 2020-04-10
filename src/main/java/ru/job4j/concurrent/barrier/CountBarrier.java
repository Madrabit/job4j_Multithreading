package ru.job4j.concurrent.barrier;

/**
 * @author madrabit
 * Blocks execution by counter condition.
 * Demonstrate notifyAll() and wait()
 */
public class CountBarrier {
    /**
     * Monitor.
     */
    private final Object monitor = this;

    /**
     * Target number.
     */
    private final int total;

    /**
     * For counter.
     */
    private int count = 0;

    public int getCount() {
        return count;
    }

    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * Increment counter and notifyAll all thread (wake up them)
     */
    public void count() {
        synchronized (monitor) {
            while (total != count) {
                count++;
                monitor.notifyAll();
            }
        }
    }


    /**
     * Thread waits while total not equals count.
     */
    public void await() {
        synchronized (monitor) {
            while (count >= total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
