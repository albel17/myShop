package myApp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categories", schema = "", catalog = "mydb")
public class CategoriesEntity {
    private int id;
    private String name;
    private String description;
    private Collection<AttributesEntity> attributes;
    private Collection<ProductsEntity> products;

    public CategoriesEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoriesEntity() {
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
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesEntity that = (CategoriesEntity) o;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "default_attributes_for_cattegories", catalog = "mydb", schema = "",
            joinColumns = @JoinColumn(name = "CATEGORIES_ID", referencedColumnName = "ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ATTRIBUTES_ID", referencedColumnName = "ID", nullable = false))
    public Collection<AttributesEntity> getAttributes() {
        return attributes;
    }

    public void setAttributes(Collection<AttributesEntity> attributes) {
        this.attributes = attributes;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, orphanRemoval = true)
    public Collection<ProductsEntity> getProducts() {
        return this.products;
    }

    public void setProducts(Collection<ProductsEntity> products) {
        this.products = products;
    }
}
