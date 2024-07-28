package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {
}
