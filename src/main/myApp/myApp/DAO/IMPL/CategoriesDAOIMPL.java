package myApp.DAO.IMPL;

import myApp.DAO.API.CategoriesDAO;
import myApp.entity.CategoriesEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class CategoriesDAOIMPL extends CategoriesDAO {
    @Override
    public ArrayList<CategoriesEntity> getAll() {
        Query query = em.createQuery("SELECT c FROM CategoriesEntity c");
        return (ArrayList<CategoriesEntity>) query.getResultList();
    }

    @Override
    public CategoriesEntity getCategoryByID(int id) {
        Query query = em.createQuery("SELECT c FROM CategoriesEntity c where c.id=:id");
        query.setParameter("id", id);
        return (CategoriesEntity) query.getSingleResult();
    }

    @Override
    public CategoriesEntity getCategoryByName(String name){
        Query query = em.createQuery("SELECT c FROM CategoriesEntity c where c.name=:name");
        query.setParameter("name", name);
        return (CategoriesEntity) query.getSingleResult();
    }

    @Override
    public Collection<CategoriesEntity> getCategoryCollectionByName(String name){
        Query query = em.createQuery("SELECT c FROM CategoriesEntity c where c.name=:name");
        query.setParameter("name", name);
        return query.getResultList();
    }
}
