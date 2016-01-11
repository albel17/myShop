package myApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "parameters", schema = "", catalog = "mydb")
public class ParametersEntity {
    private int id;
    private String value;
    private ProductsEntity productsByProductId;
    private AttributesEntity attribute;

    public ParametersEntity(String value, ProductsEntity productsByProductId, AttributesEntity attribute) {
        this.value = value;
        this.productsByProductId = productsByProductId;
        this.attribute = attribute;
    }

    public ParametersEntity() {
        //empty constructor for spring
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
    @Column(name = "VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParametersEntity that = (ParametersEntity) o;

        return id == that.id && !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID", referencedColumnName = "ID")
    public AttributesEntity getAttribute(){return  attribute;}

    public void setAttribute(AttributesEntity attribute) {
        this.attribute = attribute;
    }
}
