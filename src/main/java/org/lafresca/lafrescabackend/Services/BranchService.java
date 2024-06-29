package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Branch;
import org.lafresca.lafrescabackend.Repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) { this.branchRepository = branchRepository; }

    // Add new branch
    public String addNewBranch(Branch branch) {
        String error = null;

        if (branch.getAddress() == null || branch.getAddress().isEmpty()) {
            error = "Address cannot be empty";
        }
        else if (branch.getContactNo() == null || branch.getContactNo().isEmpty()) {
            error = "Contact No cannot be empty";
        }
        else if (branch.getLongitude() < -180 || branch.getLongitude() > 180) {
            error = "Invalid value for longitude";
        }
        else if (branch.getLatitude() < -90 || branch.getLatitude() > 90) {
            error = "Invalid value for latitude";
        }
        else if (branch.getBranchManager() == null || branch.getBranchManager().isEmpty()) {
            error = "Branch manager cannot be empty";
        }

        if (error == null) {
            branchRepository.save(branch);
        }

        return error;
    }

    // Retrieve all branches
    public List<Branch> getBranches() {
        return branchRepository.findAll();
    }

    // Search branch by id
    public Optional<Branch> getBranch(String id) { return branchRepository.findById(id); }

    // Delete branch by id
    public void deleteBranch(String id) { branchRepository.deleteById(id); }

    // Update branch by id
    public void updateBranch(String id, Branch branch) {
        Branch existingBranch = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));

        if (branch.getAddress() != null && !branch.getAddress().isEmpty()) {
            existingBranch.setAddress(branch.getAddress());
        }
        else if (branch.getContactNo() != null && !branch.getContactNo().isEmpty()) {
            existingBranch.setContactNo(branch.getContactNo());
        }
        else if (branch.getLongitude() <= 180 && branch.getLongitude() >= -180) {
            existingBranch.setLongitude(branch.getLongitude());
        }
        else if (branch.getLatitude() <= 90 && branch.getLatitude() >= -90) {
            existingBranch.setLatitude(branch.getLatitude());
        }
        else if (branch.getBranchManager() != null && !branch.getBranchManager().isEmpty()) {
            existingBranch.setBranchManager(branch.getBranchManager());
        }

        branchRepository.save(existingBranch);
    }
}
