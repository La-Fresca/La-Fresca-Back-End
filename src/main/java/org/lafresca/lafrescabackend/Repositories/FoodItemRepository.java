package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Discount;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    @Query("{ 'CafeId' : ?0, 'Deleted' : ?1 }")
    List<FoodItem> findByCafeId(String CafeId, Integer Deleted);

    @Query("{ 'id' :  ?0 }")
    FoodItem findOneById(String id);

    @Query("{ 'CafeId' : ?0, 'DiscountStatus' : ?1 }")
    List<FoodItem> findDiscountByStatus(String CafeId, int DiscountStatus);
}
