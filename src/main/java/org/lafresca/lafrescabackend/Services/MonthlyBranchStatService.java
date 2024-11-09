package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.lafresca.lafrescabackend.Repositories.MonthlyBranchStatRepository;
import org.springframework.stereotype.Service;

@Service
public class MonthlyBranchStatService {
    private final MonthlyBranchStatRepository monthlyBranchStatRepository;

    public MonthlyBranchStatService(MonthlyBranchStatRepository monthlyBranchStatRepository) {
        this.monthlyBranchStatRepository = monthlyBranchStatRepository;
    }

    public void addMonthlyBranchStat(MonthlyBranchStat monthlyBranchStat) {
        monthlyBranchStatRepository.save(monthlyBranchStat);
    }
}
