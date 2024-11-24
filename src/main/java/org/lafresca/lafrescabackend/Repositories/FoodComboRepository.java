package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodComboRepository extends MongoRepository<FoodCombo,String> {
    @Query("{ 'Status' : { $in: [0, 2] } }")
    List<FoodCombo> findByStatus();

    @Query("{ 'CafeId' :  ?0, 'Deleted' : 0 }")
    List<FoodCombo> findByCafeId(String CafeID);

    @Query("{ 'id' :  ?0 }")
    FoodCombo findOneById(String id);

    @Query("{ 'DiscountStatus' : 1, 'Deleted' : 0 }")
    List<FoodCombo> findAllByDiscountStatus();

    @Query("{ 'CafeId' :  ?0, 'DiscountStatus' : 1, 'Deleted' : 0 }")
    List<FoodCombo> findDiscountByStatus(String CafeId);
}
