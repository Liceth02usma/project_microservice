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
    private Ingredient ingredient;
    @DBRef
    private Recipe recipe;

    public IngredientsRecipe() {}

    public String get_id() {
        return _id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
