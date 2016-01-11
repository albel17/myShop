package myApp.services;

public interface GenericService<T> {
    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);
}
