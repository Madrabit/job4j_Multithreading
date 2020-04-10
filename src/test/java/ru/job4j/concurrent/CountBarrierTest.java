package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;
import ru.job4j.concurrent.barrier.CountBarrier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author madrabit
 */
public class CountBarrierTest {
    @Test
    public void test() throws InterruptedException {
        boolean result = false;
        CountBarrier barrier = new CountBarrier(100);
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
        master.join();
        
        assertEquals(Thread.State.WAITING, slave.getState());

    }
}
