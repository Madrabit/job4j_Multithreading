package ru.job4j.concurrent;

import org.junit.Test;
import ru.job4j.concurrent.barrier.CountBarrier;

import static org.junit.Assert.assertEquals;

/**
 * @author madrabit
 */
public class CountBarrierTest {

    @SuppressWarnings("unused")
    @Test
    public void test() throws InterruptedException {
        boolean result = false;
        CountBarrier barrier = new CountBarrier(50);
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

        slave.start();
        master.start();
        Thread.State state = slave.getState();
        master.join();

        assertEquals(Thread.State.WAITING, state);
    }
}
