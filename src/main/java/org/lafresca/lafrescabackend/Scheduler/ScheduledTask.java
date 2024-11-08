package org.lafresca.lafrescabackend.Scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    // Runs at 00:00 on the 1st day of every month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void runAfter31st() {
        System.out.println("Creating brach stats for the month");
        //need to create branch stats for the month

    }
}
