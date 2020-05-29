package ru.job4j.concurrent.nonblocking;

/**
 * @author madrabit
 * Custom Exception. Throws when another thread already change Base object.
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
