package com.mssecurity.mssecurity.Repositories;



import com.mssecurity.mssecurity.Models.IngredientsRecipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientsRecipeRepository extends MongoRepository<IngredientsRecipe,String> {
}

