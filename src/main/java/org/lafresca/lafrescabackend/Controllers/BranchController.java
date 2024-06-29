package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.Branch;
import org.lafresca.lafrescabackend.Services.BranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/lafresca/branch")
@AllArgsConstructor
@Tag(name = "Branch Controller")
public class BranchController {
    private final BranchService branchService;

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
                            responseCode = "403")
            })

    public String addBranch(@RequestBody Branch branch) { return branchService.addNewBranch(branch); }

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
}
