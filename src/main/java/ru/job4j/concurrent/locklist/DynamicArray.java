package ru.job4j.concurrent.locklist;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author madrabit on 09.10.2019
 * @version 1$
 * @since 0.1
 */
@SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType", "NullableProblems"})
public class DynamicArray<T> implements Iterable<T> {
    private T[] array;
    private int size = 8;
    private int position = 0;
    private int modCount = 0;
    @SuppressWarnings("unused")
    private int capacity = 8;

    public DynamicArray() {
        array = (T[]) new Object[size];
    }

    @SuppressWarnings("unused")
    public int getSize() {
        return size;
    }

    /**
     * Add element
     *
     * @param model Element for adding.
     */
    public void add(T model) {
        if (size == position) {
            increaseArray();
        }
        modCount++;
        array[position++] = model;
    }

    /**
     * Increase Array 1.5
     */
    public void increaseArray() {
        size = size + (size >> 1);
        array = Arrays.copyOf(array, size);
    }

    /**
     * Return object by index.
     *
     * @param index Index which to get.
     * @param <T>   Type of array.
     * @return Returning element.
     */
    public <T> T get(int index) {
        if (size == 0) {
            throw new NullPointerException();
        }
        if (position == 0 || index + 1 > position) {
            throw new NoSuchElementException();
        }
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            int index;

            /**
             * Check if item exists.
             *
             * @return Exists or not.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < position;
            }

            /**
             * Move cursor froward and return item.
             *
             * @return Return item.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
    }
}