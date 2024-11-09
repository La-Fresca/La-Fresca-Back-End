package org.lafresca.lafrescabackend.Scheduler;

import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.lafresca.lafrescabackend.Services.BranchService;
import org.lafresca.lafrescabackend.Services.MonthlyBranchStatService;
import org.lafresca.lafrescabackend.Services.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTask {
    private final OrderService orderService;
    private final BranchService branchService;
    private final MonthlyBranchStatService monthlyBranchStatService;

    public ScheduledTask(OrderService orderService, BranchService branchService, MonthlyBranchStatService monthlyBranchStatService) {
        this.orderService = orderService;
        this.branchService = branchService;
        this.monthlyBranchStatService = monthlyBranchStatService;
    }

    // Runs at 00:00 on the 1st day of every month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void runAfter31st() {
        System.out.println("Creating brach stats for the month");
//        List<MonthlyBranchStat> monthlyBranchStats = new ArrayList<>();

        //need to create branch stats for the month
        branchService.getBranches().forEach(branch -> {
            MonthlyBranchStat stat = orderService.getBranchStatisticsMonthly(branch.getId());
            monthlyBranchStatService.addMonthlyBranchStat(stat);
        });

    }
}
