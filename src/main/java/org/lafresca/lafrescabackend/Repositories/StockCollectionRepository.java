package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Stock;
import org.lafresca.lafrescabackend.Models.StockCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockCollectionRepository extends MongoRepository<StockCollection, String> {
    @Query("{ 'Name' : ?0 }")
    StockCollection findByNameAndCafeId(String name);

    @Query("{ 'Deleted' : ?0 }")
    List<StockCollection> findByDeleted(Integer Deleted);
}
