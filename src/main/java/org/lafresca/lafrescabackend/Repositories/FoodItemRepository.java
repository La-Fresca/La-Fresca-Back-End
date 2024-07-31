package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    @Query("{ 'Deleted' : ?0 }")
    List<FoodItem> findByDeleted(Integer Deleted);

    @Query("{'id' :  ?0}")
    FoodItem findOneById(String id);
}
