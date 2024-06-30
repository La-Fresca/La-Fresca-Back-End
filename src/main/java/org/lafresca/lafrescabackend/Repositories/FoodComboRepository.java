package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.FoodCombo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodComboRepository extends MongoRepository<FoodCombo,String> {
}
