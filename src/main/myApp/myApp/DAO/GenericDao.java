package myApp.DAO;

import java.io.Serializable;

/**
 * Created by Admin on 24.11.15.
 */
public interface GenericDao<T> {
    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);
}
