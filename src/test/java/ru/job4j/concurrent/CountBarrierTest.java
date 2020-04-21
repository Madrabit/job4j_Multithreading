package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;
import ru.job4j.concurrent.barrier.CountBarrier;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author madrabit
 */
public class CountBarrierTest {
    private Thread.State slaveState;

    @Test
    public void test() throws InterruptedException {
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
                    slaveState = Thread.currentThread().getState();
                    System.out.println(Thread.currentThread().getName() + " started");
                    assertEquals(Thread.State.RUNNABLE, Thread.currentThread().getState());
                },
                "Slave"
        );
        master.start();
        slave.start();
        master.join();
    }
}
