package ru.job4j.concurrent;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.concurrent.atomic.CASQueue;

import static org.junit.Assert.assertThat;

/**
 * @author madrabit
 */
public class CASQueueTest {
    private CASQueue<Integer> list;

    @Before
    public void beforeTest() {
        list = new CASQueue<>();
        list.push(1);
        list.push(2);
        list.push(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), Matchers.is(2));
    }

    @Test
    public void whenAddThreeElementsThenPollResultOne() {
        assertThat(list.poll(), Matchers.is(1));
    }
}
