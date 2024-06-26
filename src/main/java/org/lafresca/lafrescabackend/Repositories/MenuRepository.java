package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends MongoRepository<Menu,String> {
}
