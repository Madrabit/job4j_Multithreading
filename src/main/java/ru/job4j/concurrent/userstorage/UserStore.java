package ru.job4j.concurrent.userstorage;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author madrabit
 * Thread safe User Storage.
 * Store User and can transfer amount betwean them.
 */

@ThreadSafe
public class UserStore {
    /**
     * Users list.
     */
    private final Map<Integer, User> storage = new ConcurrentHashMap<>();

    public synchronized void add(User user) {
        storage.put(user.getId(), user);
    }

    public synchronized User update(User user) {
        return storage.put(user.getId(), user);
    }

    public synchronized User delete(User user) {
        return storage.remove(user.getId());
    }

    /**
     * Transfer money from one user to another.
     *
     * @param fromId From whom.
     * @param toId   For whom.
     * @param amount Some sum of "money".
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        User from = storage.get(fromId);
        from.setAmount(from.getAmount() - amount);
        User to = storage.get(toId);
        to.setAmount(to.getAmount() + amount);
    }
}
