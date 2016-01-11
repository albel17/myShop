package myApp.DAO.API;

import myApp.DAO.GenericDaoJpaImpl;
import myApp.entity.CategoriesEntity;

import java.util.ArrayList;
import java.util.Collection;

public abstract class CategoriesDAO extends GenericDaoJpaImpl<CategoriesEntity> {
    public abstract ArrayList<CategoriesEntity> getAll();

    public abstract CategoriesEntity getCategoryByID(int id);

    public abstract CategoriesEntity getCategoryByName(String name);

    public abstract Collection<CategoriesEntity> getCategoryCollectionByName(String name);
}
