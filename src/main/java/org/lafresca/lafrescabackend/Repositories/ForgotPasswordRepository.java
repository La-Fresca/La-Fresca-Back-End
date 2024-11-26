package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.ForgotPassword;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends MongoRepository<ForgotPassword,String> {
    @Query("{ 'email': ?0 }")
    Optional<ForgotPassword> findTopByEmailOrderByIdDesc(String email);
}
