package ru.job4j.concurrent;

/**
 * @author madrabit
 * Thread state example.
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {
                }
        );
        System.out.println(String.format("%s: %s", first.getName(), first.getState()));
        System.out.println(String.format("%s: %s", second.getName(), second.getState()));
        first.start();
        second.start();
        while (!(first.getState() == Thread.State.TERMINATED
                && second.getState() == Thread.State.TERMINATED)
        ) {
            System.out.println(String.format("%s: %s", first.getName(), first.getState()));
            System.out.println(String.format("%s: %s", second.getName(), second.getState()));
        }
        System.out.println(String.format("%s: %s", first.getName(), first.getState()));
        System.out.println(String.format("%s: %s", second.getName(), second.getState()));
        System.out.println("Finished");

    }
}
