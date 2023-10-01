package com.mssecurity.mssecurity.Repositories;


import com.mssecurity.mssecurity.Models.DishFoodOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DishFoodOrderRepository extends MongoRepository<DishFoodOrder,String> {
}

