package myApp.DAO;

import myApp.entity.OrdersEntity;
import myApp.entity.PersonsEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

@Component
public class OrdersDAO extends GenericDaoJpaImpl<OrdersEntity> {

    public ArrayList<OrdersEntity> getAllOrders() {
        Query query = em.createQuery("SELECT c FROM OrdersEntity c");
        return (ArrayList<OrdersEntity>) query.getResultList();
    }

    public ArrayList<OrdersEntity> getThisMonthOrders() {
        Query query = em.createQuery("SELECT c FROM OrdersEntity c where c.creationDate>=:date");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date date = new Date(c.getTime().getTime());
        query.setParameter("date", date);
        return (ArrayList<OrdersEntity>) query.getResultList();
    }

    public ArrayList<OrdersEntity> getThisWeekOrders() {
        Query query = em.createQuery("SELECT c FROM OrdersEntity c where c.creationDate>=:date");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        Date date = new Date(c.getTime().getTime());
        query.setParameter("date", date);
        return (ArrayList<OrdersEntity>) query.getResultList();
    }
}
