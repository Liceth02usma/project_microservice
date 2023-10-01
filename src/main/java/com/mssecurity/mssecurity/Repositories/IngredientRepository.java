package com.mssecurity.mssecurity.Repositories;


import com.mssecurity.mssecurity.Models.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientRepository extends MongoRepository<Ingredient,String> {
}

