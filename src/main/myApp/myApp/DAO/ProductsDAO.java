package myApp.DAO;

import myApp.entity.ParametersEntity;
import myApp.entity.ProductsEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ProductsDAO extends GenericDaoJpaImpl<ProductsEntity> {
    public ArrayList<String> getAllNames(){
        Query query = em.createQuery("SELECT c FROM ProductsEntity c");
        List<ProductsEntity> list = query.getResultList();
        ArrayList<String> result = new ArrayList<String>();
        for(ProductsEntity c : list){
            result.add(c.getName());
        }
        return result;
    }

    public ArrayList<String> getProductInfoByID(int id){
        Query query = em.createQuery("SELECT c FROM ProductsEntity c where c.id=:id");
        query.setParameter("id", id);
        ProductsEntity product = (ProductsEntity) query.getSingleResult();
        ArrayList<String> result = new ArrayList<String>();
        result.add(product.getName());
        result.add(product.getCurrentPrice());
        result.add(product.getDescription());
        Collection<ParametersEntity> parameters = product.getParametersesById();
        for(ParametersEntity p : parameters){
            result.add(p.getValue());
        }
        return result;
    }

    public ProductsEntity getProductByID(int id){
        Query query = em.createQuery("SELECT c FROM ProductsEntity c where c.id=:id");
        query.setParameter("id", id);
        ProductsEntity product = (ProductsEntity) query.getSingleResult();
        return product;
    }

    public ArrayList<ProductsEntity> getProductsByCategoryID(String category){
        Query query = em.createQuery("SELECT c FROM ProductsEntity c where c.category=:id");
        query.setParameter("id", Integer.parseInt(category));
        ArrayList<ProductsEntity> result = (ArrayList<ProductsEntity>) query.getResultList();
        return result;
    }
}
