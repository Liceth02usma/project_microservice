package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.DishFood;
import com.mssecurity.mssecurity.Repositories.DishFoodRepository;

@CrossOrigin
@RestController
@RequestMapping("private/dishfood")
public class DishFoodController {
    @Autowired
    private DishFoodRepository theDishFoodRepository;

    @GetMapping("menu")
    public List<DishFood> index() {
        return this.theDishFoodRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DishFood store(@RequestBody DishFood newDishFood) {
        return this.theDishFoodRepository.save(newDishFood);
    }
}
