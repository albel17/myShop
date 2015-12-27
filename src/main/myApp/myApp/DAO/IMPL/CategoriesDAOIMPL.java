package myApp.DAO.IMPL;

import myApp.DAO.API.CategoriesDAO;
import myApp.entity.CategoriesEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;

@Component
public class CategoriesDAOIMPL extends CategoriesDAO {
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
