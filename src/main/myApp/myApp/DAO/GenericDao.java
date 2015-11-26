package myApp.DAO;

import java.io.Serializable;

public interface GenericDao<T> {
    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);
}
