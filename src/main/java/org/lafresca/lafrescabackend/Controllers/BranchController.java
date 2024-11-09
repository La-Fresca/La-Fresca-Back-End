package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.DTO.BranchStat;
import org.lafresca.lafrescabackend.DTO.Request.BranchRequestDTO;
import org.lafresca.lafrescabackend.Models.Branch;
import org.lafresca.lafrescabackend.Models.MonthlyBranchStat;
import org.lafresca.lafrescabackend.Services.BranchService;
import org.lafresca.lafrescabackend.Services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/lafresca/branch")
@AllArgsConstructor
@Validated
@Tag(name = "Branch Controller")
public class BranchController {
    private final BranchService branchService;
    private final OrderService orderService;

    // Add new branch
    @PostMapping
    @Operation(
            description = "Add new branch",
            summary = "Add new branch to the chain",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public ResponseEntity<BranchRequestDTO> addNewBranch(@Valid @RequestBody BranchRequestDTO branch) {
        return ResponseEntity.status(201).body(branchService.addNewBranch(branch));
    }

    // Get all branches
    @GetMapping
    @Operation(
            description = "Get all branches",
            summary = "Retrieve all branches in the chain",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public List<Branch> getBranches() { return branchService.getBranches(); }

    // Search branch
    @GetMapping(path = "{id}")
    @Operation(
            description = "Search branch by id",
            summary = "Retrieve branches by using id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public Optional<Branch> getBranch(@PathVariable("id") String id) { return branchService.getBranch(id); }

    // Delete branch
    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete branch",
            summary = "Delete branch by using id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public void deleteBranch(@PathVariable("id") String id) { branchService.deleteBranch(id); }

    // Update Branch
    @PutMapping(path = "{id}")
    @Operation(
            description = "Update branch",
            summary = "Update branch by using id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            })

    public void updateBranch(@PathVariable("id") String id, @RequestBody Branch branch) {
        branchService.updateBranch(id, branch);
    }

    // Logical Delete
    @PutMapping(path = "delete/{id}")
    @Operation(
            description = "Logically delete branch by id",
            summary = "Logically delete branches by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })

    public void logicallyDeleteBranch(@PathVariable("id") String id, @RequestBody Branch branch){
        branchService.logicallyDeleteBranch(id, branch);
    }

    @GetMapping(value = "/branchStatistics/{id}")
    @Operation(
            description = "Get branch statistics",
            summary = "Get statistics of a branch by using the id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403")
            })
    public BranchStat getBranchStatistics(@PathVariable("id") String id) {
        return orderService.getBranchStatistics(id);
    }

    @GetMapping(value = "/branchStatistics/monthly/{id}")
    @Operation(
            description = "Get monthly branch statistics",
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
    public MonthlyBranchStat getBranchStatisticsMonthly(@PathVariable("id") String id) {
        return orderService.getBranchStatisticsMonthly(id);
    }
}