package com.mssecurity.mssecurity.Repositories;



import com.mssecurity.mssecurity.Models.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe,String> {}

