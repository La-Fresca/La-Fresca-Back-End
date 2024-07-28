package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    @Query("{'UserId' : ?0 }")
    List<Cart> findByUserId(String userId);
}
