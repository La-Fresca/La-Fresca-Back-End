package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem,String> {
}
