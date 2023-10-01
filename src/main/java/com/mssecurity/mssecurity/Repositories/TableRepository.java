package com.mssecurity.mssecurity.Repositories;



import com.mssecurity.mssecurity.Models.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TableRepository extends MongoRepository<Table,String> {
}

