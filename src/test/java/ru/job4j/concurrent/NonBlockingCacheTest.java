package ru.job4j.concurrent;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.concurrent.nonblocking.Base;
import ru.job4j.concurrent.nonblocking.NonBlockingCache;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

/**
 * @author madrabit
 */
public class NonBlockingCacheTest {
    @Test
    public void testError() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        NonBlockingCache cache = new NonBlockingCache();
        cache.add(new Base(1));
        Thread first = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1));
                    } catch (Exception e) {
                        ex.set(e);
                    }

                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        Assert.assertThat(ex.get().getMessage(), is("Not updated"));
        Assert.assertThat(cache.getMap().get(1).getVersion(), is(1));
    }
}
