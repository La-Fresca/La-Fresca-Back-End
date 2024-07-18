package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
}
