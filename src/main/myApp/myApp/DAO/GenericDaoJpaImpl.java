package myApp.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericDaoJpaImpl<T>
        implements GenericDao<T> {

    private Class<T> type;

    @PersistenceContext
    protected EntityManager em;

    public GenericDaoJpaImpl() {
        //Type t = getClass().getGenericSuperclass();
        //ParameterizedType pt = (ParameterizedType) t;
        //type = (Class) pt.getActualTypeArguments()[0];
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

    //@Transactional
    public T create(final T t) {
        em.persist(t);
        return t;
    }

    public void delete(final Object id) {
        em.getTransaction().begin();
        em.remove(em.getReference(type, id));
        em.flush();
        em.getTransaction().commit();
    }

    public T find(final Object id) {
        return (T) em.find(type, id);
    }

    public T update(final T t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        return t;
    }
}
