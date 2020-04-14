package ru.job4j.concurrent;

import org.junit.Test;
import ru.job4j.concurrent.userstorage.User;
import ru.job4j.concurrent.userstorage.UserStore;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author madrabit
 */
public class UserStorageTest {
    @Test
    public void test() {
        UserStore storage = new UserStore();
        User first = new User(1, 100);
        User second = new User(2, 200);

        storage.add(first);
        storage.add(second);

        storage.transfer(1, 2, 50);
        assertThat(first.getAmount(), is(50));
        assertThat(second.getAmount(), is(250));
    }
}
