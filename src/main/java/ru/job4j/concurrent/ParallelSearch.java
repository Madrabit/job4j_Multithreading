package ru.job4j.concurrent;

import ru.job4j.concurrent.blockingqueue.SimpleBlockingQueue;

/**
 * @author madrabit
 */
public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty()) {
                        System.out.println(queue.poll());
                    }
                }
        );

        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                    consumer.interrupt();
                }

        );
        producer.start();
        consumer.start();

    }
}

