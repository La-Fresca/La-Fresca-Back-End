package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodComboRepository extends MongoRepository<FoodCombo,String> {
    @Query("{ 'CafeId' :  ?0, 'Deleted' : ?1 }")
    List<FoodCombo> findByCafeId(String CafeID, Integer Deleted);

    @Query("{ 'id' :  ?0 }")
    FoodCombo findOneById(String MenuItemId);

    @Query("{ 'CafeId' :  ?0, 'DiscountStatus' : 1, 'Deleted' : 0 }")
    List<FoodCombo> findDiscountByStatus(String CafeId);
}
