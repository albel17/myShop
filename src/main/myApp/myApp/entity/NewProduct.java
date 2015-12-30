package myApp.entity;

import java.util.ArrayList;

public class NewProduct {
    private String name;
    private String currentPrice;
    private String size;
    private String weight;
    private String description;
    private ArrayList<String> newAttributes = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getNewAttributes() {
        return newAttributes;
    }

    public void setNewAttributes(ArrayList<String> newAttributes) {
        this.newAttributes = newAttributes;
    }
}
