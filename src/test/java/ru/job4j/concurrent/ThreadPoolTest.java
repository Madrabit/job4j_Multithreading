package ru.job4j.concurrent;

import org.junit.Test;
import ru.job4j.concurrent.pool.ThreadPool;
import java.util.concurrent.atomic.AtomicInteger;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * @author madrabit
 */
public class ThreadPoolTest {
    @Test
    public void when4ThreadIncrement0ThenReturns4() {
        int size = Runtime.getRuntime().availableProcessors();
        AtomicInteger count = new AtomicInteger();
        ThreadPool threadPool = new ThreadPool(size);
        threadPool.work(() -> count.getAndIncrement());
        threadPool.work(() -> count.getAndIncrement());
        threadPool.work(() -> count.getAndIncrement());
        threadPool.work(() -> count.getAndIncrement());
        threadPool.shutdown();
        assertThat(count.get(), is(4));
    }

    @Test
    public void sameTestWithFinalArray() {
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
