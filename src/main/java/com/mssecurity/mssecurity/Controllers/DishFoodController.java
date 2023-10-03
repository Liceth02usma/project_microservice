package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.DishFood;
import com.mssecurity.mssecurity.Models.Recipe;
import com.mssecurity.mssecurity.Repositories.DishFoodRepository;
import com.mssecurity.mssecurity.Repositories.RecipeRepository;

@CrossOrigin
@RestController
@RequestMapping("")
public class DishFoodController {
    @Autowired
    private DishFoodRepository theDishFoodRepository;

    @Autowired
    private RecipeRepository theRecipeRepository;

    @GetMapping("public/menu")
    public List<DishFood> index() {
        return this.theDishFoodRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("private/dishfood")
    public DishFood store(@RequestBody DishFood newDishFood) {
        return this.theDishFoodRepository.save(newDishFood);
    }

    @GetMapping("public/dishfood/{id}")
    public DishFood show(@PathVariable String id) {
        DishFood theDishFood = this.theDishFoodRepository.findById(id).orElse(null);
        return theDishFood;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("private/dishfood/{id}")
    public DishFood update(@PathVariable String id, @RequestBody DishFood newDishFood) {
        DishFood theDishFood = this.theDishFoodRepository.findById(id).orElse(null);

        if (theDishFood != null) {
            theDishFood.setName(newDishFood.getName());
            theDishFood.setDescription(newDishFood.getDescription());
            theDishFood.setValue(newDishFood.getValue());
            theDishFood.setRecipe(newDishFood.getRecipe());
            return this.theDishFoodRepository.save(theDishFood);
        } else {
            return null;
        }

    }

    @DeleteMapping("private/dishfood/{id}")
    public void destroy(@PathVariable String id) {
        DishFood theDishFood = this.theDishFoodRepository.findById(id).orElse(null);
        if (theDishFood != null) {
            this.theDishFoodRepository.delete(theDishFood);
        }
    }

    @PutMapping("private/dishfood/{dishfood_id}/recipe/{recipe_id}")
    public DishFood matchDishfoodRecipe(@PathVariable String dishfood_id, @PathVariable String recipe_id) {

        DishFood theDishFood = this.theDishFoodRepository.findById(dishfood_id).orElse(null);
        Recipe theRecipe = this.theRecipeRepository.findById(recipe_id).orElse(null);

        if ((theDishFood != null) && (theRecipe != null)) {
            theDishFood.setRecipe(theRecipe);
            return this.theDishFoodRepository.save(theDishFood);
        } else {
            return null;
        }
    }

    @PutMapping("private/dishfood/{dishfood_id}/recipe")
    public DishFood unMatchDishfoodRecipe(@PathVariable String dishfood_id) {

        DishFood theDishFood = this.theDishFoodRepository.findById(dishfood_id).orElse(null);

        if (theDishFood != null) {
            theDishFood.setRecipe(null);
            return this.theDishFoodRepository.save(theDishFood);
        } else {
            return null;
        }
    }

}
