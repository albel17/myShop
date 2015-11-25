package myApp.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericDaoJpaImpl<T>
        implements GenericDao<T> {

    private Class<T> type;

    @PersistenceContext
    protected EntityManager em;

    public GenericDaoJpaImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
        this.em = emf.createEntityManager();
    }

    public T create(final T t) {
        this.em.persist(t);
        return t;
    }

    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }

    public T find(final Object id) {
        return (T) this.em.find(type, id);
    }

    public T update(final T t) {
        return this.em.merge(t);
    }
}
