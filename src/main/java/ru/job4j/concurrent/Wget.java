package ru.job4j.concurrent;

/**
 * @author madrabit
 * Simulation of load process. Using Thread sleep().
 */
public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        Thread.sleep(1000);
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(500);
                            System.out.print("\rLoading : " + i + "%");
                        }
                        System.out.println(System.lineSeparator() + "Loaded.");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
