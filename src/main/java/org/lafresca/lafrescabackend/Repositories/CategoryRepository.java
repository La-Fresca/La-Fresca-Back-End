package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query("{ 'CafeId' : ?0, 'Name' : ?1 }")
    Category findByName(String CafeId, String Name);

    @Query("{ 'CafeId' : ?0 }")
    List<Category> findByCafeId(String CafeId);
}
