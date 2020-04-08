package ru.job4j.concurrent;

/**
 * @author madrabit
 * Shows how Thread#start() works.
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();
//        another.run();
        another.start();
        System.out.println(Thread.currentThread().getName());
    }
}
