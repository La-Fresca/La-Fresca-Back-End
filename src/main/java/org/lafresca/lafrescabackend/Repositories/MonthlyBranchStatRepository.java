package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyBranchStatRepository extends MongoRepository<MonthlyBranchStat,String> {
}
