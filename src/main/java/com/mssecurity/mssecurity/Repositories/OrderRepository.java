package com.mssecurity.mssecurity.Repositories;



import com.mssecurity.mssecurity.Models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}

