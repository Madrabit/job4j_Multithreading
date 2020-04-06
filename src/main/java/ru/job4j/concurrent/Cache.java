package ru.job4j.concurrent;

/**
 * @author madrabit
 * Demonstration how to make method atomic with "synchronized".
 */
public final class Cache {
    private static Cache cache;

    @SuppressWarnings("unused")
    private static synchronized Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
