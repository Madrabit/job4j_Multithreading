package ru.job4j.concurrent.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author madrabit
 * Non Blocking Cache.
 */
public class NonBlockingCache {

    /**
     * Storage.
     */
    private final ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    public ConcurrentHashMap<Integer, Base> getMap() {
        return map;
    }

    public void add(Base model) {
        map.put(model.getId(), model);
    }

    /**
     * Update Base if version expected.
     *
     * @param model Updated Base.
     * @throws OptimisticException Custom Exception. Throws when another thread already change Base object.
     */
    public void update(Base model) throws OptimisticException {
        map.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != value.getVersion()) {
                throw new OptimisticException("Not updated");
            }
            model.setVersion(value.getVersion() + 1);
            return model;
        });
    }

    public void delete(Base model) {
        map.remove(model.getId());
    }
}
