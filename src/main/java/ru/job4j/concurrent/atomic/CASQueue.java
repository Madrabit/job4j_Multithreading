package ru.job4j.concurrent.atomic;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Thread Safe Queue. Base on Atomic References and Compare and Swap atomic instruction.
 *
 * @param <E>
 */
@ThreadSafe
public class CASQueue<E> {

    /**
     * Queue head.
     */
    private final AtomicReference<CASQueue.Node<E>> head = new AtomicReference<>();
    /**
     * Queue tail.
     */
    private final AtomicReference<CASQueue.Node<E>> tail = new AtomicReference<>();

    /**
     * Queue size.
     */
    private int size;
    /**
     * For checking modification in Iterator.
     */
    private int modCount;

    /**
     * Method insert data at the end of the list.
     */
    public void push(E data) {
        CASQueue.Node<E> newLink = new CASQueue.Node<>(data);
        CASQueue.Node<E> ref;
        CASQueue.Node<E> oldLink;
        do {
            ref = head.get();
            if (ref == null) {
                head.set(newLink);
                tail.set(newLink);
                break;
            } else {
                oldLink = tail.get();
                oldLink.prev = newLink;
            }
        } while (!tail.compareAndSet(oldLink, newLink));
        this.size++;
        modCount++;
    }

    /**
     * Get head element from Queue.
     *
     * @return Head data.
     */
    public E poll() {
        CASQueue.Node<E> ref;
        CASQueue.Node<E> temp;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalStateException("Queue is empty");
            }
            temp = ref.prev;
        } while (!head.compareAndSet(ref, temp));
        ref.prev = null;
        return ref.data;
    }

    /**
     * Get element by index.
     */
    public E get(int index) {
        CASQueue.Node<E> result = null;
        for (int i = -1; i < index; i++) {
            result = head.get();
            head.compareAndSet(result, result.prev);
        }
        return result.data;
    }

    /**
     * Get collection size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Data storage.
     */
    private static class Node<E> {
        final E data;
        CASQueue.Node<E> next;
        CASQueue.Node<E> prev;

        Node(E data) {
            this.data = data;
        }
    }
}
