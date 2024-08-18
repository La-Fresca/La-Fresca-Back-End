package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Discount;
import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.lafresca.lafrescabackend.Models.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodComboRepository extends MongoRepository<FoodCombo,String> {
    @Query("{ 'Deleted' : ?0 }")
    List<FoodCombo> findByDeleted(Integer Deleted);

    @Query("{ 'id' :  ?0 }")
    FoodCombo findOneById(String menuItemId);

    @Query("{ 'DiscountStatus' : ?0 }")
    List<Discount> findByStatus(int i);

    @Query("{ 'CafeId' : ?0 }")
    List<FoodCombo> findByCafeId(Long cafeId);
}
