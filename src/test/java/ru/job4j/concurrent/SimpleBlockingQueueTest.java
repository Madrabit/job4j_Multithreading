package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;
import ru.job4j.concurrent.blockingqueue.Producer;
import ru.job4j.concurrent.blockingqueue.Consumer;
import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author madrabit
 */

public class SimpleBlockingQueueTest {
    @Test
    public void whenOffer4Poll3ThenExpectedSize3() {
        SimpleBlockingQueue<Integer> blockingQueue = new SimpleBlockingQueue<>(4);

        Producer<Integer> producer = new Producer<>(blockingQueue);
        Consumer<Integer> consumer = new Consumer<>(blockingQueue);

        producer.offer(1);
        producer.offer(2);
        producer.offer(3);
        producer.offer(4);

        consumer.poll();
        consumer.poll();
        consumer.poll();

        assertThat(consumer.getStorage().size(), is(3));

    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> IntStream.range(0, 9).forEach(
                        queue::offer
                )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)));
    }
}
