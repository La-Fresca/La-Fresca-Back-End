package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    @Query("{ 'CafeId' : ?0, 'Status' : { $in: [0, 2] } }")
    List<FoodItem> findByCafeIdAndStatus(String CafeId);

    @Query("{ 'CafeId' : ?0, 'Status' : 0 }")
    List<FoodItem> findByCafeId(String CafeId);

    @Query("{ 'id' :  ?0 }")
    FoodItem findOneById(String id);

    @Query("{ 'CafeId' : ?0, 'DiscountStatus' : 1, 'Status' : 0 }")
    List<FoodItem> findDiscountByStatus(String CafeId);
}
