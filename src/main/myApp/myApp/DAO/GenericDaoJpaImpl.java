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
    protected static EntityManager em;

    private void getEntityManager(){
        if(em==null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
            em = emf.createEntityManager();
        }
    }

    protected void getNewEntityManager(){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
            em = emf.createEntityManager();
    }

    public GenericDaoJpaImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];

        getEntityManager();
    }

    public T create(final T t) {
        em.getTransaction().begin();
        em.persist(t);
        em.flush();
        em.getTransaction().commit();
        getNewEntityManager();
        return t;
    }

    public void delete(final Object id) {
        em.getTransaction().begin();
        em.remove(em.getReference(type, id));
        em.flush();
        em.getTransaction().commit();
        getNewEntityManager();
    }

    public T find(final Object id) {
        return (T) em.find(type, id);
    }

    public T update(final T t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        getNewEntityManager();
        return t;
    }
}
