package com.mssecurity.mssecurity.Repositories;

import com.mssecurity.mssecurity.Models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation,String> {}

