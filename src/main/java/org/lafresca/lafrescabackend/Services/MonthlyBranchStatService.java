package org.lafresca.lafrescabackend.Services;

import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.lafresca.lafrescabackend.Repositories.MonthlyBranchStatRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;

@Service
@Slf4j
public class MonthlyBranchStatService {
    private final MonthlyBranchStatRepository monthlyBranchStatRepository;
    private final SystemLogService systemLogService;

    public MonthlyBranchStatService(MonthlyBranchStatRepository monthlyBranchStatRepository, SystemLogService systemLogService) {
        this.monthlyBranchStatRepository = monthlyBranchStatRepository;
        this.systemLogService = systemLogService;
    }

    public void addMonthlyBranchStat(MonthlyBranchStat monthlyBranchStat) {
        MonthlyBranchStat savedMonthlyBranchStat = monthlyBranchStatRepository.save(monthlyBranchStat);

        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Monthly Branch Statistics added (id: " + savedMonthlyBranchStat.getId() + ")";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);
    }

    public List<MonthlyBranchStat> getBranchStatisticsEveryMonth(String id) {
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Retrieve Branch Statistics by branch id (id: " + id + ")";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

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

        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Retrieve Branch Statistics in 12 months related to cafe id (id: " + id + ")";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

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

        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Retrieve every Branch Statistics in 12 months ";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

        return monthlyBranchStats;
    }

    public List<MonthlyBranchStat> getBranchStatisticsPreviousMonths(String id) {
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Retrieve Branch Statistics of previous month related to cafe id (id: " + id + ")";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

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

        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Retrieve Branch Statistics in 12 months related to branch id (id: " + id + ")";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

        return branchStatList;
    }
}
