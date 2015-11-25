package myApp;

import javax.persistence.*;

/**
 * Created by Admin on 22.11.15.
 */
@Entity
@Table(name = "storage", schema = "", catalog = "mydb")
public class StorageEntity {
    private int id;
    private int amount;
    private ProductsEntity productsByProductId;

    @Id
    @Column(name = "ID")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageEntity that = (StorageEntity) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        return result;
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
