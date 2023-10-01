package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Recipe;
import com.mssecurity.mssecurity.Repositories.RecipeRepository;

@CrossOrigin
@RestController
@RequestMapping("private/recipe")
public class RecipeController {
    @Autowired
    private RecipeRepository theRecipeRepository;



    @GetMapping("")
    public List<Recipe> index() {return this.theRecipeRepository.findAll();}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Recipe store(@RequestBody Recipe newRecipe) {
        return this.theRecipeRepository.save(newRecipe);
    }

    @GetMapping("{id}")
    public Recipe show(@PathVariable String id) {
        Recipe theRecipe = this.theRecipeRepository
                .findById(id)
                .orElse(null);
        return theRecipe;
    }

    @PutMapping("{id}")
    public Recipe update(@PathVariable String id, @RequestBody Recipe theNewRecipe) {
        Recipe theActualRecipe = this.theRecipeRepository
                .findById(id)
                .orElse(null);
        if (theActualRecipe != null) {
            theActualRecipe.setName(theNewRecipe.getName());
            theActualRecipe.setInstructions(theNewRecipe.getInstructions());
            return this.theRecipeRepository.save(theActualRecipe);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Recipe theRecipe = this.theRecipeRepository
                .findById(id)
                .orElse(null);
        if (theRecipe != null) {
            this.theRecipeRepository.delete(theRecipe);
        }
    }


}
