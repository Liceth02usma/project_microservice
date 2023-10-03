package com.mssecurity.mssecurity.Repositories;

import com.mssecurity.mssecurity.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{'_id': ?0}")
    public User getUserByID(String _id);
}
