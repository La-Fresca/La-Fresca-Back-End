package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {
    @Query("{ 'Deleted' : 0 }")
    List<Branch> findByDeleted();
}
