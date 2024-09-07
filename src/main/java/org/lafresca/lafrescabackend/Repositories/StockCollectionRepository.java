package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.StockCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockCollectionRepository extends MongoRepository<StockCollection, String> {
    @Query("{ 'CafeId' : ?0, 'Name' : ?1, 'Deleted' : 0  }")
    StockCollection findByName(String CafeId, String Name);

    @Query("{ 'CafeId' : ?0, 'Deleted' : 0 }")
    List<StockCollection> findByCafeId(String CafeId);
}
