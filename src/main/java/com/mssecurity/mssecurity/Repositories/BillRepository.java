package com.mssecurity.mssecurity.Repositories;

import com.mssecurity.mssecurity.Models.Bill;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill,String> {
}

