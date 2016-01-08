package myApp.form;

import myApp.entity.ProductsEntity;

public class ProductsMoney {
    ProductsEntity productsEntity;
    int money;

    public ProductsMoney(ProductsEntity productsEntity, int money) {
        this.productsEntity = productsEntity;
        this.money = money;
    }

    public ProductsEntity getProductsEntity() {
        return productsEntity;
    }

    public void setProductsEntity(ProductsEntity productsEntity) {
        this.productsEntity = productsEntity;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
