package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.lafresca.lafrescabackend.Repositories.MonthlyBranchStatRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
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
            System.out.println("Current Month: " + currentMonth);
            YearMonth yearMonth = currentMonth.minusMonths(i);
            String monthText = yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            System.out.println("Month: " + monthText);
            String year = String.valueOf(yearMonth.getYear());
            System.out.println("Year: " + year);
            MonthlyBranchStat monthlyBranchStat = monthlyBranchStatRepository.findByCafeIdAndMonth(id, monthText, year);
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
            System.out.println("Year Month: " + yearMonth);
            String year = String.valueOf(yearMonth.getYear());
            String month = yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            List<MonthlyBranchStat> monthlyBranchStat = monthlyBranchStatRepository.findByMonth( month, year);
            if (monthlyBranchStat != null) {
                monthlyBranchStats.addAll(monthlyBranchStat);
            }
        }

        return monthlyBranchStats;
    }

    public List<MonthlyBranchStat> getBranchStatisticsPreviousMonths(String id) {
        return monthlyBranchStatRepository.findByCafeId(id);
    }

    public List<MonthlyBranchStat> getBranchStatisticsPrevious12Months(String id) {
        List<MonthlyBranchStat> branchStatList = new ArrayList<>();

        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM"); // Format for the month
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");  // Format for the year
        Calendar calendar = Calendar.getInstance();

        MonthlyBranchStat monthlyBranchStat = new MonthlyBranchStat();

        for (int i = 0; i < 12; i++) {
            String month = monthFormat.format(calendar.getTime()); // Get the month
            String year = yearFormat.format(calendar.getTime());
            monthlyBranchStat = monthlyBranchStatRepository.findByCafeIdAndMonth(id, month,year);
            if(monthlyBranchStat != null){
                branchStatList.add(monthlyBranchStat);
            }
            calendar.add(Calendar.MONTH, -1); // Move to the previous month
        }

        System.out.println("length of branch Stat: "+ branchStatList.size());

        return branchStatList;
    }
}
