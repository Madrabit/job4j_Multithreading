package ru.job4j.concurrent;

import org.junit.Test;
import ru.job4j.concurrent.pool.ThreadPool;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * @author madrabit
 */
public class ThreadPoolTest {
    @Test
    public void sameTestWithFinalArray() throws InterruptedException {
        int size = Runtime.getRuntime().availableProcessors();
        final int[] count = {0};
        ThreadPool threadPool = new ThreadPool(size);
        threadPool.work(() -> count[0]++);
        threadPool.work(() -> count[0]++);
        threadPool.work(() -> count[0]++);
        threadPool.work(() -> count[0]++);
        threadPool.shutdown();
        assertThat(count[0], is(4));
    }
}
