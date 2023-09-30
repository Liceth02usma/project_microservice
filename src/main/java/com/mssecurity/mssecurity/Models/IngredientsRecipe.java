package com.mssecurity.mssecurity.Models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class IngredientsRecipe {
    @Id
    private String _id;
    @DBRef
    private Ingredients ingredient;
    @DBRef
    private Recipe recipe;


    public IngredientsRecipe(Ingredients ingredient, Recipe recipe) {
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    public String get_id() {
        return _id;
    }

    public Ingredients getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredients ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
