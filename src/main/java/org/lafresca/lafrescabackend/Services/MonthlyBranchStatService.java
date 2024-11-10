package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.lafresca.lafrescabackend.Repositories.MonthlyBranchStatRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.YearMonth;
import java.util.*;

@Service
public class MonthlyBranchStatService {
    private final MonthlyBranchStatRepository monthlyBranchStatRepository;

    public MonthlyBranchStatService(MonthlyBranchStatRepository monthlyBranchStatRepository) {
        this.monthlyBranchStatRepository = monthlyBranchStatRepository;
    }

    public void addMonthlyBranchStat(MonthlyBranchStat monthlyBranchStat) {
        monthlyBranchStatRepository.save(monthlyBranchStat);
    }

    public List<MonthlyBranchStat> getBranchStatisticsEveryMonth(String id) {
        return monthlyBranchStatRepository.findByCafeId(id);
    }

    public List<MonthlyBranchStat> getBranchStatisticsThisYear(String id) {
        YearMonth currentMonth = YearMonth.now();

        List<MonthlyBranchStat> monthlyBranchStats = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            YearMonth yearMonth = currentMonth.minusMonths(i);
            MonthlyBranchStat monthlyBranchStat = monthlyBranchStatRepository.findByCafeIdAndMonth(id, yearMonth.getMonthValue(), yearMonth.getYear());
            if (monthlyBranchStat != null) {
                monthlyBranchStats.add(monthlyBranchStat);
            }
        }

        return monthlyBranchStats;
    }

    public List<MonthlyBranchStat> getBranchStatisticsAllThisYear() {
        YearMonth currentMonth = YearMonth.now();

        List<MonthlyBranchStat> monthlyBranchStats = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            YearMonth yearMonth = currentMonth.minusMonths(i);
            MonthlyBranchStat monthlyBranchStat = monthlyBranchStatRepository.findByMonth( yearMonth.getMonthValue(), yearMonth.getYear());
            if (monthlyBranchStat != null) {
                monthlyBranchStats.add(monthlyBranchStat);
            }
        }

        return monthlyBranchStats;
    }
}
