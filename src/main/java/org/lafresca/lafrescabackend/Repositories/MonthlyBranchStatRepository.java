package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyBranchStatRepository extends MongoRepository<MonthlyBranchStat,String> {
    @Query("{ 'branchId' : ?0 }")
    List<MonthlyBranchStat> findByCafeId(String id);

    @Query("{ 'branchId' : ?0, 'Month' : ?1, 'Year' : ?2 }")
    MonthlyBranchStat findByCafeIdAndMonth(String id, String month, String year);

    @Query("{ 'Month' : ?0, 'Year' : ?1 }")
    List<MonthlyBranchStat> findByMonth(String month, String year);

}
