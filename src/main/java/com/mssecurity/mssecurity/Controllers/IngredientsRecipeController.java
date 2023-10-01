package com.mssecurity.mssecurity.Controllers;

import com.mssecurity.mssecurity.Models.*;
import com.mssecurity.mssecurity.Repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.IngredientsRecipe;
import com.mssecurity.mssecurity.Repositories.IngredientsRecipeRepository;

@CrossOrigin
@RestController
@RequestMapping("private/ingredients")
public class IngredientsRecipeController {
    @Autowired
    private IngredientsRecipeRepository theIngredientsRecipeRepository;
    @Autowired
    private IngredientsRepository theIngredientsRepository;
    @Autowired
    private RecipeRepository theRecipeRepository;


    @GetMapping("")
    public List<IngredientsRecipe> index() {return this.theIngredientsRecipeRepository.findAll();}



///// PENDIENTE CREATE

    @GetMapping("{id}")
    public IngredientsRecipe show(@PathVariable String id) {
        IngredientsRecipe theIngredientsRecipe = this.theIngredientsRecipeRepository
                .findById(id)
                .orElse(null);
        return theIngredientsRecipe;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("ingredients/{ingredients_id}/Recipe/{recipe_id}")
    public IngredientsRecipe store(@PathVariable String ingredients_id,
                                @PathVariable String Recipe_id) {
        Ingredients theIngredients=this.theIngredientsRepository.findById(ingredients_id)
                .orElse(null);
        Recipe theRecipe=this.theRecipeRepository.findById(Recipe_id)
                .orElse(null);
        if(theIngredients!=null && theRecipe!=null){
            IngredientsRecipe newIngredientsRecipe=new IngredientsRecipe();
            newIngredientsRecipe.setIngredient(theIngredients);
            newIngredientsRecipe.setRecipe(theRecipe);
            return this.theIngredientsRecipeRepository.save(newIngredientsRecipe);
        }else{
            return null;
        }
    }

    @PutMapping("{id}")
    public IngredientsRecipe update(@PathVariable String id, @RequestBody IngredientsRecipe theNewIngredientsRecipe) {
        IngredientsRecipe theActualIngredientsRecipe = this.theIngredientsRecipeRepository
                .findById(id)
                .orElse(null);
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
        IngredientsRecipe theIngredientsRecipe = this.theIngredientsRecipeRepository
                .findById(id)
                .orElse(null);
        if (theIngredientsRecipe != null) {
            this.theIngredientsRecipeRepository.delete(theIngredientsRecipe);
        }
    }


}
