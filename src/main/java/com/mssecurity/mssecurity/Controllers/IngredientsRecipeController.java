package com.mssecurity.mssecurity.Controllers;

import com.mssecurity.mssecurity.Models.Ingredients;
import com.mssecurity.mssecurity.Models.IngredientsRecipe;
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
