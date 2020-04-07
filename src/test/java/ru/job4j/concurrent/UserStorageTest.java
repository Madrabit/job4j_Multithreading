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

        storage.add(new User(1, 100));
        storage.add(new User(2, 200));

        storage.transfer(1, 2, 50);
        assertThat(storage.getStorage().get(1).getAmount(), is(50));
        assertThat(storage.getStorage().get(2).getAmount(), is(250));
    }
}
