package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<Food,String> {
}
