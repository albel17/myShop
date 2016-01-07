package myApp.DAO.API;

import myApp.DAO.GenericDaoJpaImpl;
import myApp.entity.CategoriesEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public abstract class CategoriesDAO extends GenericDaoJpaImpl<CategoriesEntity> {
    public abstract ArrayList<CategoriesEntity> getAll();

    public abstract CategoriesEntity getCategoryByID(int id);

    public abstract CategoriesEntity getCategoryByName(String name);
}
