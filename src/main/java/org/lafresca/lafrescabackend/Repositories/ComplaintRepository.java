package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplaintRepository extends MongoRepository<Complaint, String> {
}
