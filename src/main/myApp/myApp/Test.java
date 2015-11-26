package myApp;

import myApp.entity.PersonsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.11.15.
 */
public class Test {

    /*
    public static void main(String[] args){
        PersonsDAO personsDAO = new PersonsDAO();
        System.out.println(personsDAO.find(1));

        CategoriesDAO categoriesDAO = new CategoriesDAO();
        System.out.println(categoriesDAO.getAll());
    }
    */

    public static List<String> getClients(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");
        EntityManager em = emf.createEntityManager();
        List<String> list = new ArrayList<String>();

        List<PersonsEntity> users = em.createQuery("select u from PersonsEntity u", PersonsEntity.class).getResultList();

        for(PersonsEntity u: users){
            System.out.println(u.getName());
            list.add(u.getName());
        }

        em.close();
        emf.close();
        return list;
    }
}