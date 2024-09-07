package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
    @Query("{ 'CafeId' :  ?0, 'StockCollectionName' : ?1, 'Deleted' :  0}")
    List<Stock> findByName(String CafeId, String Name);

    @Query("{ 'CafeId' :  ?0, 'Deleted' : 0 }")
    List<Stock> findByCafeId(String CafeId);
}
