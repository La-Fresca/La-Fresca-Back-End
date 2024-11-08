package org.lafresca.lafrescabackend.Services;

import jakarta.validation.Valid;
import org.lafresca.lafrescabackend.DTO.BranchStat;
import org.lafresca.lafrescabackend.DTO.Request.BranchRequestDTO;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Branch;
import org.lafresca.lafrescabackend.Models.BranchStatus;
import org.lafresca.lafrescabackend.Models.IncomeCost;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.BranchRepository;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class BranchService {
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository, UserRepository userRepository) { this.branchRepository = branchRepository;
        this.userRepository = userRepository;
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

        return branch;
    }

    // Retrieve all branches
    public List<Branch> getBranches() {
        return branchRepository.findByDeleted();
    }

    // Search branch by id
    public Optional<Branch> getBranch(String id) {
        branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));
        return branchRepository.findById(id);
    }

    // Delete branch by id
    public void deleteBranch(String id) {
        branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));
        branchRepository.deleteById(id);
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
    }

    // Logical Delete
    public void logicallyDeleteBranch(String id) {
        Branch existingBranch = branchRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Branch not found with id " + id));

        existingBranch.setDeleted(1);

        branchRepository.save(existingBranch);
    }

    //branch statistics
//    public BranchStat getBranchStat(String id) {
//        Date today  = new Date();
//
//    }
}