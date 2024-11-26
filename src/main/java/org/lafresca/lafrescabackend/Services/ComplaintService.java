package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Branch;
import org.lafresca.lafrescabackend.Models.Complaint;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.BranchRepository;
import org.lafresca.lafrescabackend.Repositories.ComplaintRepository;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository, UserRepository userRepository, BranchRepository branchRepository) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
    }

    public String addNewComplaint(Complaint complaint) {
        try {
            complaint.setDate(LocalDateTime.now());

            User complainer = userRepository.findByUserId(complaint.getComplainer());
            if(complainer == null){
                throw new ResourceNotFoundException("User not found");
            }

            Optional<Branch> branch = branchRepository.findById(complaint.getComplainee());
            if(branch == null){
                throw new ResourceNotFoundException("Cafe not found");
            }
            Complaint newComplaint = complaintRepository.save(complaint);

            return newComplaint.toString();
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Error saving complaint", e);
        }
    }

    // Retrieve all complaints
    public List<Complaint> getComplaints() {
        return complaintRepository.findAll();
    }

    //delete complaint
    public String deleteComplaint(String id) {
        complaintRepository.deleteById(id);

        return "Complaint deleted";
    }

    public List<Complaint> GetComplaintsByComplainer(String id) {
//        System.out.println("Id " + id);
        return complaintRepository.findAllByComplainerId(id);
    }

    public List<Complaint> GetComplaintsByComplainee(String id) {
        return complaintRepository.findAllByComplaineeId(id);
    }
}
