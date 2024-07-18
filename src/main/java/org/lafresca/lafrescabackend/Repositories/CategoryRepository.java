package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
