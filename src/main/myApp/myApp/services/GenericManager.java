package myApp.services;

public interface GenericManager<T> {
    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);
}
