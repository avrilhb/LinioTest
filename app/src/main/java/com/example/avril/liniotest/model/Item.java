package com.example.avril.liniotest.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by avrilhb on 04/01/18.
 */

public class Item {

    Map<String, ProductCharacteristics> products;
    private int id;
    private String name;
    private String description;
    private Owner owner;
    private String createdAt;
    private String visibility;

    public Map<String, ProductCharacteristics> getProducts() {
        return products;
    }

    public void setProducts(Map<String, ProductCharacteristics> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

}
