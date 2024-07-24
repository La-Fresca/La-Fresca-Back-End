package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
}
