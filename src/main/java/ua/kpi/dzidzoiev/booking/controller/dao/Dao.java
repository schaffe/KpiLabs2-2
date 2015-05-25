package ua.kpi.dzidzoiev.booking.controller.dao;

import java.io.Serializable;

/**
 * Created by dzidzoiev on 2/22/15.
 */
public interface Dao<T> extends Serializable {
    T get(Integer id);
    void update(T t);
    void create(T t);
    void delete(T t);
}
