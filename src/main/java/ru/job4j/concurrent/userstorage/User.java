package ru.job4j.concurrent.userstorage;

import net.jcip.annotations.ThreadSafe;

/**
 * @author madrabit
 * Simple user with amount.
 */

@ThreadSafe
public class User {
    /**
     * User Id
     */
    private int id;
    /**
     * User amount. (Like money on account).
     */
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized int getAmount() {
        return amount;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }
}
