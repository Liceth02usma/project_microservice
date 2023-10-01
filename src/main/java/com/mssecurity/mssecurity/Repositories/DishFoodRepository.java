package com.mssecurity.mssecurity.Repositories;

import com.mssecurity.mssecurity.Models.DishFood;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DishFoodRepository extends MongoRepository<DishFood,String> {
}

