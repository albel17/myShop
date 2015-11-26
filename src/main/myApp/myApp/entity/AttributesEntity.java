package myApp.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Admin on 22.11.15.
 */
@Entity
@Table(name = "attributes", schema = "", catalog = "mydb")
public class AttributesEntity {
    private int id;
    private String name;
    private String description;
    private Collection<CategoriesEntity> categories;

    @Id
    @Column(name = "ID")
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

        AttributesEntity that = (AttributesEntity) o;

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

    @ManyToMany(mappedBy = "attributes")
    public Collection<CategoriesEntity> getCategories() {
        return categories;
    }

    @OneToMany(mappedBy = "attribute")
    private Collection<ParametersEntity> attributes;

    public void setCategories(Collection<CategoriesEntity> categories) {
        this.categories = categories;
    }
}