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
            this.em = emf.createEntityManager();
        }
    }

    private void getNewEntityManager(){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
            this.em = emf.createEntityManager();
    }

    public GenericDaoJpaImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];

        getEntityManager();
    }

    public T create(final T t) {
        this.em.getTransaction().begin();
        this.em.persist(t);
        this.em.flush();
        this.em.getTransaction().commit();
        getNewEntityManager();
        return t;
    }

    public void delete(final Object id) {
        this.em.getTransaction().begin();
        this.em.remove(this.em.getReference(type, id));
        this.em.flush();
        this.em.getTransaction().commit();
        getNewEntityManager();
    }

    public T find(final Object id) {
        return (T) this.em.find(type, id);
    }

    public T update(final T t) {
        this.em.getTransaction().begin();
        this.em.merge(t);
        this.em.getTransaction().commit();
        return t;
    }
}
