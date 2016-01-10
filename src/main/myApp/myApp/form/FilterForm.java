package myApp.form;

import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;

import java.util.ArrayList;
import java.util.Collection;

public class FilterForm {
    private int categoryId;
    private int minPrice;
    private int maxPrice;
    private String name;
    private ArrayList<AttributeAndValue> attributesAndValues = new ArrayList<AttributeAndValue>();

    public FilterForm(int categoryId, int minPrice, int maxPrice, String name, CategoriesEntity category) {
        this.categoryId = categoryId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.name = name;
        Collection<AttributesEntity> attributes = category.getAttributes();
        for (AttributesEntity attribute : attributes) {
            attributesAndValues.add(new AttributeAndValue(attribute, ""));
        }
    }

    public void update(CategoriesEntity category) {
        Collection<AttributesEntity> attributes = category.getAttributes();
        int i = 0;
        for (AttributesEntity attribute : attributes) {
            attributesAndValues.get(i).setAttribute(attribute);
        }
    }

    public FilterForm() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<AttributeAndValue> getAttributesAndValues() {
        return attributesAndValues;
    }

    public void setAttributesAndValues(ArrayList<AttributeAndValue> attributesAndValues) {
        this.attributesAndValues = attributesAndValues;
    }

    @Override
    public String toString() {
        return "FilterForm{" +
                "categoryId=" + categoryId +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", name='" + name + '\'' +
                ", attributesAndValues=" + attributesAndValues +
                '}';
    }
}
