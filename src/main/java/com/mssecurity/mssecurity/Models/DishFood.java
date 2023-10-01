package com.mssecurity.mssecurity.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class DishFood {
    @Id
    private String _id;

    private String name;
    private String description;
    private int value;

    @DBRef
    private Recipe recipe;

    public DishFood(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String get_id() {
        return _id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
