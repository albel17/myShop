package myApp.bin;

import myApp.DAO.ProductsDAO;
import myApp.entity.ProductsEntity;

public class CartItem {
    private ProductsEntity product;
    private int amount;
    private boolean notEnough;

    public CartItem(ProductsEntity product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public boolean isNotEnough() {
        return notEnough;
    }

    public void setNotEnough(boolean notEnough) {
        this.notEnough = notEnough;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public int getProductId() {
        return product.getId();
    }

    public String getProductName() {
        return product.getName();
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId(){
        return this.product.getId();
    }
}
