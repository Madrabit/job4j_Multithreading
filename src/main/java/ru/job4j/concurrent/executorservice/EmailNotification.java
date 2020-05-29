package ru.job4j.concurrent.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author madrabit
 * Email sender with Thread Pool.
 */
public class EmailNotification {

    /**
     * Pool of Threads.
     */
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * Convert Notification string and Send.
     *
     * @param user Target user.
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification %s to email %s.", user.getUsername(), user.getEmail());
            String body = String.format("Add a new event to %s", user.getUsername());
            send(subject, body, user.getEmail());
        });

    }

    /**
     * Close pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send prepared email.
     *
     * @param subject Email subject.
     * @param body    Email text body.
     * @param email   @mail.
     */
    public void send(String subject, String body, String email) {
        //
    }

}
