package org.lafresca.lafrescabackend.Services;

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

    public List<Branch> getBranches() {
        return branchRepository.findAll();
    }

    public Optional<Branch> getBranch(String id) { return branchRepository.findById(id); }

    public void deleteBranch(String id) { branchRepository.deleteById(id); }
}
