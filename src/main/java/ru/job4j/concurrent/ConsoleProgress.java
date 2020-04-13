package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

/**
 * @author madrabit
 * Console Preloader.
 * Showing Thread interrupt()
 */
public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.print("\r load: " + "-");
                Thread.sleep(500);
                System.out.print("\r load: " + "\\");
                Thread.sleep(500);
                System.out.print("\r load: " + "|");
                Thread.sleep(500);
                System.out.print("\r load: " + "/");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    public static void main(String[] args) {
        Thread progress = new Thread(
                new ConsoleProgress()
        );
        progress.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            progress.interrupt();
        }
        progress.interrupt();

    }

}
