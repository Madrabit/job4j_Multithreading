package ru.job4j.concurrent.nonblocking;

/**
 * @author madrabit
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
