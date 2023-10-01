package com.mssecurity.mssecurity.Repositories;


import com.mssecurity.mssecurity.Models.Ingredients;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientsRepository extends MongoRepository<Ingredients,String> {
}

