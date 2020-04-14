package ru.job4j.concurrent.nonblocking;

/**
 * @author madrabit
 * Base object with id and version (to compare them in Non Blocking Cache).
 */
public class Base {
    private int id;
    private int version = 0;

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
