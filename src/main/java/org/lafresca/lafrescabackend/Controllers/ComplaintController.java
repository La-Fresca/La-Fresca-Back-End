package org.lafresca.lafrescabackend.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.lafresca.lafrescabackend.Models.Complaint;
import org.lafresca.lafrescabackend.Services.ComplaintService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/lafresca/complaint")
@AllArgsConstructor
@Tag(name = "Complaint Controller")
public class ComplaintController {
    private final ComplaintService complaintService;

    // Add new item
    @PostMapping
    @Operation(
            description = "Add to complaint",
            summary = "Add new complaint",
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
    public String addNewComplaint(@RequestBody Complaint complaint) { return complaintService.addNewComplaint(complaint); }

}
