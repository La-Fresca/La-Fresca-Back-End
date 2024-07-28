package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
    @Query("{'StockCollectionName' : ?0 }")
    List<Stock> findByName(String name);
}
