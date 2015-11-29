package myApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_item", schema = "", catalog = "mydb")
public class OrderItemEntity {
    private int id;
    private int amount;
    private int price;
    private OrdersEntity ordersByOrderId;
    private ProductsEntity productsByProductId;

    public OrderItemEntity(int amount, int price, OrdersEntity ordersByOrderId, ProductsEntity productsByProductId) {
        this.amount = amount;
        this.price = price;
        this.ordersByOrderId = ordersByOrderId;
        this.productsByProductId = productsByProductId;
    }

    public OrderItemEntity() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AMOUNT")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "PRICE")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItemEntity that = (OrderItemEntity) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        return price == that.price;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        result = 31 * result + price;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false)
    public OrdersEntity getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(OrdersEntity ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    public ProductsEntity getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(ProductsEntity productsByProductId) {
        this.productsByProductId = productsByProductId;
    }
}
