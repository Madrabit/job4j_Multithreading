package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;
import ru.job4j.concurrent.blockingqueue.Producer;
import ru.job4j.concurrent.blockingqueue.Consumer;
import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author madrabit
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SimpleBlockingQueueTest {
    @Test
    public void whenOffer4Poll3ThenExpectedSize3() {
        SimpleBlockingQueue blockingQueue = new SimpleBlockingQueue(4);

        @SuppressWarnings("rawtypes") Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        producer.offer(1);
        producer.offer(2);
        producer.offer(3);
        producer.offer(4);

        consumer.poll();
        consumer.poll();
        consumer.poll();

        assertThat(consumer.getStorage().size(), is(3));

    }
}
