package myApp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "products", schema = "", catalog = "mydb")
public class ProductsEntity {
    private int id;
    private String name;
    private String currentPrice;
    private String size;
    private String weight;
    private String description;
    private CategoriesEntity category;
    private Collection<OrderItemEntity> orderItemsById;
    private Collection<ParametersEntity> parametersesById;
    private Collection<StorageEntity> storagesById;

    public ProductsEntity(String name, String currentPrice, String size, String weight, String description, CategoriesEntity category) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.size = size;
        this.weight = weight;
        this.description = description;
        this.category = category;
    }

    public ProductsEntity() {
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
    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Basic
    @Column(name = "SIZE")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "WEIGHT")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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
        if (currentPrice != null ? !currentPrice.equals(that.currentPrice) : that.currentPrice != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productsByProductId")
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

    @OneToMany(mappedBy = "productsByProductId")
    public Collection<StorageEntity> getStoragesById() {
        return storagesById;
    }

    public void setStoragesById(Collection<StorageEntity> storagesById) {
        this.storagesById = storagesById;
    }

/*
    private CategoriesEntity categoriesEntities;

    @ManyToOne(optional = false)
    public CategoriesEntity getCategoriesEntities() {
        return categoriesEntities;
    }

    public void setCategoriesEntities(CategoriesEntity categoriesEntities) {
        this.categoriesEntities = categoriesEntities;
    }
    */
}
