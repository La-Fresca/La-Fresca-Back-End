package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ComplaintRepository extends MongoRepository<Complaint, String> {
    @Query("{'Complainer': ?0}")
    List<Complaint> findAllByComplainerId(String id);

    @Query("{'Complainee': ?0}")
    List<Complaint> findAllByComplaineeId(String id);
}
