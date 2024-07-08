package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Discount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends MongoRepository<Discount, String> {

}
