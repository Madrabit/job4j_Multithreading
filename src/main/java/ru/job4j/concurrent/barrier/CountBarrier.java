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
                System.out.println(count);
            }
        }
    }


    /**
     * Thread waits while total not equals count.
     */
    public void await() {
        synchronized (monitor) {
            while (total != count) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier barrier = new CountBarrier(10000);
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}
