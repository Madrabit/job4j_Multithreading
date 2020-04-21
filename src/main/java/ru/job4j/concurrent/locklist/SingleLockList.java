package ru.job4j.concurrent.locklist;


import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * @author madrabit
 * Concurrent collection.
 */

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    /**
     * Dynamic array for all threads.
     */
    private final DynamicArray<T> list = new DynamicArray<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    private synchronized DynamicArray<T> copy(DynamicArray<T> list) {
        DynamicArray<T> newList = new DynamicArray<>();
        list.forEach(newList::add);
        return newList;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
