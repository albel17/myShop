package myApp.DAO;

import myApp.CategoriesEntity;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 24.11.15.
 */
public class CategoriesDAO extends GenericDaoJpaImpl<CategoriesEntity> {
    public ArrayList<String> getAllNames(){
        Query query = em.createQuery("SELECT c FROM CategoriesEntity c");
        List<CategoriesEntity> list = query.getResultList();
        ArrayList<String> result = new ArrayList<String>();
        for(CategoriesEntity c : list){
            result.add(c.getName());
        }
        return result;
    }

    public ArrayList<CategoriesEntity> getAll(){
        Query query = em.createQuery("SELECT c FROM CategoriesEntity c");
        ArrayList<CategoriesEntity> result = (ArrayList<CategoriesEntity>) query.getResultList();
        return result;
    }
}
