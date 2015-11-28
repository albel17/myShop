package myApp.bin;

import myApp.entity.ProductsEntity;

public class OrderItem {
    private ProductsEntity product;
    private int amount;

    public OrderItem(ProductsEntity product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getName(){
        return product.getName();
    }

    public int getProductId(){
        return product.getId();
    }

    public ProductsEntity getProduct() {
        return product;
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
}
