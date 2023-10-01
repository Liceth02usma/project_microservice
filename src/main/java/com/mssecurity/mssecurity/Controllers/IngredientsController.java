package com.mssecurity.mssecurity.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Ingredient;
import com.mssecurity.mssecurity.Repositories.IngredientRepository;

@CrossOrigin
@RestController
@RequestMapping("private/ingredient")
public class IngredientsController {
    @Autowired
    private IngredientRepository theIngredientRepository;

    @GetMapping("")
    public List<Ingredient> index() {return this.theIngredientRepository.findAll();}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Ingredient store(@RequestBody Ingredient newIngredient) {
        return this.theIngredientRepository.save(newIngredient);
    }

    @GetMapping("{id}")
    public Ingredient show(@PathVariable String id) {
        Ingredient theIngredient = this.theIngredientRepository
                .findById(id)
                .orElse(null);
        return theIngredient;
    }

    @PutMapping("{id}")
    public Ingredient update(@PathVariable String id, @RequestBody Ingredient theNewIngredient) {
        Ingredient theActualIngredient = this.theIngredientRepository
                .findById(id)
                .orElse(null);
        if (theActualIngredient != null) {
            theActualIngredient.setName(theNewIngredient.getName());
            theActualIngredient.setAmount(theNewIngredient.getAmount());
            theActualIngredient.setCategory(theNewIngredient.getCategory());
            theActualIngredient.setDateInquisition(theNewIngredient.getDateInquisition());
            return this.theIngredientRepository.save(theActualIngredient);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Ingredient theIngredient = this.theIngredientRepository
                .findById(id)
                .orElse(null);
        if (theIngredient != null) {
            this.theIngredientRepository.delete(theIngredient);
        }
    }


}
