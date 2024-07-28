package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.StockCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCollectionRepository extends MongoRepository<StockCollection, String> {
    @Query("{'Name' : ?0 }")
    StockCollection findByName(String name);
}
