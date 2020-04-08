package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;
import ru.job4j.concurrent.blockingqueue.Consumer;
import ru.job4j.concurrent.blockingqueue.Producer;
import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;

/**
 * @author madrabit
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SimpleBlockingQueueTest {
    @Test
    public void testConsumer() {
        SimpleBlockingQueue blockingQueue = new SimpleBlockingQueue(4);

        @SuppressWarnings("rawtypes") Consumer consumer = new Consumer(blockingQueue);
        Producer producer = new Producer(blockingQueue);

        consumer.offer(1);
        consumer.offer(2);
        consumer.offer(3);
        consumer.offer(4);

        producer.poll();
        producer.poll();
        producer.poll();
    }

    @Test
    public void testProducer() {
        SimpleBlockingQueue blockingQueue = new SimpleBlockingQueue(4);

        Consumer consumer = new Consumer(blockingQueue);
        Producer producer = new Producer(blockingQueue);


        consumer.offer(1);
        consumer.offer(2);
        consumer.offer(3);


        producer.poll();
        producer.poll();
        producer.poll();
        producer.poll();
    }
}
