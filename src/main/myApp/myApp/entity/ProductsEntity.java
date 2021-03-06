package myApp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "products", schema = "", catalog = "mydb")
public class ProductsEntity {
    private int id;
    private String name;
    private int currentPrice;
    private int size;
    private int weight;
    private String description;
    private CategoriesEntity category;
    private Collection<OrderItemEntity> orderItemsById;
    private Collection<ParametersEntity> parametersesById;
    private StorageEntity storagesById;

    public ProductsEntity(String name, int currentPrice, int size, int weight, String description,
                          CategoriesEntity category) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.size = size;
        this.weight = weight;
        this.description = description;
        this.category = category;
    }

    public ProductsEntity() {
        //spring constructor
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CURRENT_PRICE")
    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Basic
    @Column(name = "SIZE")
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Basic
    @Column(name = "WEIGHT")
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    public CategoriesEntity getCategory() {
        return category;
    }

    public void setCategory(CategoriesEntity category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productsByProductId", fetch = FetchType.EAGER)
    public Collection<OrderItemEntity> getOrderItemsById() {
        return orderItemsById;
    }

    public void setOrderItemsById(Collection<OrderItemEntity> orderItemsById) {
        this.orderItemsById = orderItemsById;
    }

    @OneToMany(mappedBy = "productsByProductId")
    public Collection<ParametersEntity> getParametersesById() {
        return parametersesById;
    }

    public void setParametersesById(Collection<ParametersEntity> parametersesById) {
        this.parametersesById = parametersesById;
    }

    @OneToOne(mappedBy = "productsByProductId", cascade = CascadeType.ALL)
    public StorageEntity getStoragesById() {
        return storagesById;
    }

    public void setStoragesById(StorageEntity storagesById) {
        this.storagesById = storagesById;
    }

}
