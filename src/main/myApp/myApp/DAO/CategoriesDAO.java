package myApp.DAO;

import myApp.entity.CategoriesEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.ArrayList;

@Component
public class CategoriesDAO extends GenericDaoJpaImpl<CategoriesEntity> {
    public ArrayList<CategoriesEntity> getAll() {
        Query query = em.createQuery("SELECT c FROM CategoriesEntity c");
        return (ArrayList<CategoriesEntity>) query.getResultList();
    }

    public CategoriesEntity getCategoryByID(int id) {
        Query query = em.createQuery("SELECT c FROM CategoriesEntity c where c.id=:id");
        query.setParameter("id", id);
        return (CategoriesEntity) query.getSingleResult();
    }
}
