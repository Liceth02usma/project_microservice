package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Ingredient;
import com.mssecurity.mssecurity.Models.IngredientsRecipe;
import com.mssecurity.mssecurity.Models.Recipe;
import com.mssecurity.mssecurity.Repositories.IngredientRepository;
import com.mssecurity.mssecurity.Repositories.IngredientsRecipeRepository;
import com.mssecurity.mssecurity.Repositories.RecipeRepository;

@CrossOrigin
@RestController
@RequestMapping("private/ingredient-recipe")
public class IngredientsRecipeController {
    @Autowired
    private IngredientsRecipeRepository theIngredientsRecipeRepository;
    @Autowired
    private RecipeRepository theRecipeRepository;
    @Autowired
    private IngredientRepository theIngredientRepository;

    @GetMapping("")
    public List<IngredientsRecipe> index() {
        return this.theIngredientsRecipeRepository.findAll();
    }

    @GetMapping("{id}")
    public IngredientsRecipe show(@PathVariable String id) {
        IngredientsRecipe theIngredientsRecipe = this.theIngredientsRecipeRepository.findById(id).orElse(null);
        return theIngredientsRecipe;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("recipe/{recipe_id}/ingredient/{ing_id}")
    public IngredientsRecipe store(@PathVariable String ing_id, @PathVariable String recipe_id) {
        Ingredient theIngredient = this.theIngredientRepository.findById(ing_id).orElse(null);
        Recipe theRecipe = this.theRecipeRepository.findById(recipe_id).orElse(null);

        if ((theIngredient != null) && (theRecipe != null)) {
            IngredientsRecipe newIngRecipe = new IngredientsRecipe();
            newIngRecipe.setIngredient(theIngredient);
            newIngRecipe.setRecipe(theRecipe);
            return this.theIngredientsRecipeRepository.save(newIngRecipe);
        } else {
            return null;
        }
    }

    @PutMapping("{id}")
    public IngredientsRecipe update(@PathVariable String id, @RequestBody IngredientsRecipe theNewIngredientsRecipe) {
        IngredientsRecipe theActualIngredientsRecipe = this.theIngredientsRecipeRepository.findById(id).orElse(null);
        if (theActualIngredientsRecipe != null) {
            theActualIngredientsRecipe.setIngredient(theNewIngredientsRecipe.getIngredient());
            theActualIngredientsRecipe.setRecipe(theNewIngredientsRecipe.getRecipe());
            return this.theIngredientsRecipeRepository.save(theActualIngredientsRecipe);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        IngredientsRecipe theIngRecipe = this.theIngredientsRecipeRepository.findById(id).orElse(null);
        if (theIngRecipe != null) {
            this.theIngredientsRecipeRepository.delete(theIngRecipe);
        }
    }

}
