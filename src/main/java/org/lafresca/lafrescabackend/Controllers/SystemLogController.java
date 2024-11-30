package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.lafresca.lafrescabackend.Services.SystemLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/lafresca/systemLog")
@AllArgsConstructor
@Validated
@Tag(name = "System Log Controller")
public class SystemLogController {
    private final SystemLogService systemLogService;

    @GetMapping(value = "/allTime/{id}")
    @Operation(
            description = "Get every monthly branch statistics",
            summary = "Get full system log",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public List<String> getSystemLogAllTime() {
        List<String> syslogs = new ArrayList<>();
        return syslogs;
    }
}
