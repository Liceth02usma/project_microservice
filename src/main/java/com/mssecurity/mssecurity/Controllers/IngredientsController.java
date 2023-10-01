package com.mssecurity.mssecurity.Controllers;

import com.mssecurity.mssecurity.Models.Ingredients;
import com.mssecurity.mssecurity.Repositories.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("private/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientsRepository theIngredientsRepository;



    @GetMapping("")
    public List<Ingredients> index() {return this.theIngredientsRepository.findAll();}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Ingredients store(@RequestBody Ingredients newIngredients) {
        return this.theIngredientsRepository.save(newIngredients);
    }

    @GetMapping("{id}")
    public Ingredients show(@PathVariable String id) {
        Ingredients theIngredients = this.theIngredientsRepository
                .findById(id)
                .orElse(null);
        return theIngredients;
    }

    @PutMapping("{id}")
    public Ingredients update(@PathVariable String id, @RequestBody Ingredients theNewIngredients) {
        Ingredients theActualIngredients = this.theIngredientsRepository
                .findById(id)
                .orElse(null);
        if (theActualIngredients != null) {
            theActualIngredients.setName(theNewIngredients.getName());
            theActualIngredients.setAmount(theNewIngredients.getAmount());
            theActualIngredients.setCategory(theNewIngredients.getCategory());
            theActualIngredients.setDateInquisition(theNewIngredients.getDateInquisition());
            return this.theIngredientsRepository.save(theActualIngredients);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Ingredients theIngredients = this.theIngredientsRepository
                .findById(id)
                .orElse(null);
        if (theIngredients != null) {
            this.theIngredientsRepository.delete(theIngredients);
        }
    }


}
