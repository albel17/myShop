package myApp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DefaultAttributesForCattegoriesEntityPK implements Serializable {
    private int attributesId;
    private int categoriesId;

    @Column(name = "ATTRIBUTES_ID")
    @Id
    public int getAttributesId() {
        return attributesId;
    }

    public void setAttributesId(int attributesId) {
        this.attributesId = attributesId;
    }

    @Column(name = "CATEGORIES_ID")
    @Id
    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultAttributesForCattegoriesEntityPK that = (DefaultAttributesForCattegoriesEntityPK) o;

        if (attributesId != that.attributesId) return false;
        if (categoriesId != that.categoriesId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attributesId;
        result = 31 * result + categoriesId;
        return result;
    }
}
