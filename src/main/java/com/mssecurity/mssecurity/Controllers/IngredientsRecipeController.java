package com.mssecurity.mssecurity.Controllers;

import com.mssecurity.mssecurity.Models.*;
import com.mssecurity.mssecurity.Repositories.IngredientsRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("private/ingredients")
public class IngredientsRecipeController {
    @Autowired
    private IngredientsRecipeRepository theIngredientsRecipeRepository;



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
        Permission thePermission=this.theRecipeRepository.findById(Recipe_id)
                .orElse(null);
        if(theIngredients!=null && thePermission!=null){
            IngredientsRecipe newIngredientsRecipe=new IngredientsRecipe();
            newIngredientsRecipe.setIngredient(theIngredients);
            newIngredientsRecipe.setPermission(thePermission);
            return this.theIngredientsRecipeRepository.save(newRolePermission);
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
