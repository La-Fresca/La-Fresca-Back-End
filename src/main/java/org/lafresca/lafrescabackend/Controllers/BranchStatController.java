package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.lafresca.lafrescabackend.Services.BranchService;
import org.lafresca.lafrescabackend.Services.MonthlyBranchStatService;
import org.lafresca.lafrescabackend.Services.OrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/lafresca/branchStat")
@AllArgsConstructor
@Validated
@Tag(name = "Monthly Branch Statistic Controller")
public class BranchStatController {
    private final BranchService branchService;
    private final MonthlyBranchStatService monthlyBranchStatService;

    @GetMapping(value = "/allTime/{id}")
    @Operation(
            description = "Get every monthly branch statistics",
            summary = "Get monthly statistics of a branch by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public List<MonthlyBranchStat> getBranchStatisticsAllTime(@PathVariable("id") String id) {
        return monthlyBranchStatService.getBranchStatisticsPreviousMonths(id);
    }

    @GetMapping(value = "/12Months/{id}")
    @Operation(
            description = "Get last 12 month branch statistics",
            summary = "Get last 12 month statistics of a branch by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public List<MonthlyBranchStat> getBranchStatistics12Months(@PathVariable("id") String id) {
        return monthlyBranchStatService.getBranchStatisticsPrevious12Months(id);
    }
}
