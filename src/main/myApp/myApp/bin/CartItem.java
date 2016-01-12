package myApp.bin;

import myApp.entity.ProductsEntity;

public class CartItem {
    //product
    private ProductsEntity product;

    //products amount in the cart
    private int amount;

    //true if we have enough items in the storage
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
