package myApp.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericDaoJpaImpl<T>
        implements GenericDao<T> {

    private Class<T> type;

    @PersistenceContext
    protected EntityManager em;

    public GenericDaoJpaImpl() {
        Type t = getClass().getGenericSuperclass();

        ParameterizedType pt = null;
        while (pt == null) {
            if (t instanceof ParameterizedType) {
                pt = (ParameterizedType) t;
            } else {
                t = ((Class<?>) t).getGenericSuperclass();
            }
        }
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T create(final T t) {
        em.persist(t);
        return t;
    }

    @Override
    public void delete(final Object id) {
        em.remove(em.getReference(type, id));
    }

    @Override
    public T find(final Object id) {
        return (T) em.find(type, id);
    }

    @Override
    public T update(final T t) {
        em.merge(t);
        return t;
    }
}
