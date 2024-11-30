package org.lafresca.lafrescabackend.Services;

import jakarta.validation.Valid;
import org.lafresca.lafrescabackend.DTO.BranchStat;
import org.lafresca.lafrescabackend.DTO.Request.BranchRequestDTO;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.*;
import org.lafresca.lafrescabackend.Repositories.BranchRepository;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class BranchService {
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final SystemLogService systemLogService;

    @Autowired
    public BranchService(BranchRepository branchRepository, UserRepository userRepository, SystemLogService systemLogService) { this.branchRepository = branchRepository;
        this.userRepository = userRepository;
        this.systemLogService = systemLogService;
    }

    // Add new branch
    public BranchRequestDTO addNewBranch(@Valid BranchRequestDTO branch) {
        Branch newBranch = new Branch();
        newBranch.setAddress(branch.getAddress());
        newBranch.setContactNo(branch.getContactNo());
        newBranch.setLatitude(branch.getLatitude());
        newBranch.setLongitude(branch.getLongitude());
        newBranch.setBranchManager(branch.getBranchManager());
        newBranch.setName(branch.getName());
        newBranch.setStatus(BranchStatus.CLOSED);

        User branchManager =  userRepository.findById(branch.getBranchManager()).orElseThrow(() -> new ResourceNotFoundException("Branch manager not found with id " + branch.getBranchManager()));

        newBranch.setDeleted(0);
        Branch savedBranch = branchRepository.save(newBranch);
        branchManager.setCafeId(savedBranch.getId());
        userRepository.save(branchManager);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Created New Branch - " + savedBranch.getId();
        systemLogService.writeToFile(message);
        return branch;
    }

    // Retrieve all branches
    public List<Branch> getBranches() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Retrieve all branches" ;
        systemLogService.writeToFile(message);

        return branchRepository.findByDeleted();
    }

    // Search branch by id
    public Optional<Branch> getBranch(String id) {
        branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Retrieve branche by Id - " + id;
        systemLogService.writeToFile(message);

        return branchRepository.findById(id);
    }

    // Delete branch by id
    public void deleteBranch(String id) {
        branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));
        branchRepository.deleteById(id);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Delete branch - " + id ;
        systemLogService.writeToFile(message);
    }

    // Update branch by id
    public void updateBranch(String id, Branch branch) {
        Branch existingBranch = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));

        if (branch.getAddress() != null && !branch.getAddress().isEmpty()) {
            existingBranch.setAddress(branch.getAddress());
        }
        if (branch.getContactNo() != null && !branch.getContactNo().isEmpty()) {
            existingBranch.setContactNo(branch.getContactNo());
        }
        if (branch.getLongitude() <= 180 && branch.getLongitude() >= -180) {
            existingBranch.setLongitude(branch.getLongitude());
        }
        if (branch.getLatitude() <= 90 && branch.getLatitude() >= -90) {
            existingBranch.setLatitude(branch.getLatitude());
        }
        if (branch.getBranchManager() != null && !branch.getBranchManager().isEmpty()) {
            existingBranch.setBranchManager(branch.getBranchManager());
        }

        branchRepository.save(existingBranch);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Updated branch " + id ;
        systemLogService.writeToFile(message);
    }

    // Logical Delete
    public void logicallyDeleteBranch(String id) {
        Branch existingBranch = branchRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Branch not found with id " + id));

        existingBranch.setDeleted(1);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Deleted branch logically - " + id ;
        systemLogService.writeToFile(message);

        branchRepository.save(existingBranch);
    }

}