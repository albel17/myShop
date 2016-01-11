package myApp.entity;

import java.util.ArrayList;

public class NewProduct {
    private String name;
    private int currentPrice;
    private int size;
    private int weight;
    private String description;
    private ArrayList<String> newAttributes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
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
