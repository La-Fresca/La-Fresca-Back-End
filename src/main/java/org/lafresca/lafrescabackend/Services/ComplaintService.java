package org.lafresca.lafrescabackend.Services;

import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.Exceptions.ResourceNotFoundException;
import org.lafresca.lafrescabackend.Models.Branch;
import org.lafresca.lafrescabackend.Models.Complaint;
import org.lafresca.lafrescabackend.Models.User;
import org.lafresca.lafrescabackend.Repositories.BranchRepository;
import org.lafresca.lafrescabackend.Repositories.ComplaintRepository;
import org.lafresca.lafrescabackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final SystemLogService systemLogService;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository, UserRepository userRepository, BranchRepository branchRepository, SystemLogService systemLogService) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.systemLogService = systemLogService;
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

            String user = SecurityContextHolder.getContext().getAuthentication().getName();
            LocalDateTime now = LocalDateTime.now();

            String message = now + " " + user + " " + "Created new complaint (id: " + newComplaint.getId() + ")" ;
            systemLogService.writeToFile(message);

            return newComplaint.toString();
        } catch (Exception e) {
            // Log the exception
            String user = SecurityContextHolder.getContext().getAuthentication().getName();
            LocalDateTime now = LocalDateTime.now();

            String message = now + " " + user + " " + "Tried to make complaint but failed due to " + e ;
            systemLogService.writeToFile(message);

            throw new RuntimeException("Error saving complaint", e);
        }
    }

    // Retrieve all complaints
    public List<Complaint> getComplaints() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Get all complaints" ;
        systemLogService.writeToFile(message);
        return complaintRepository.findAll();
    }

    //delete complaint
    public String deleteComplaint(String id) {
        complaintRepository.deleteById(id);

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Deleted complaint (id: " + id + ")" ;
        systemLogService.writeToFile(message);

        return "Complaint deleted";
    }

    public List<Complaint> GetComplaintsByComplainer(String id) {
//        System.out.println("Id " + id);
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Get complaints complained created by complainer (id: " + id + ")" ;
        systemLogService.writeToFile(message);

        return complaintRepository.findAllByComplainerId(id);
    }

    public List<Complaint> GetComplaintsByComplainee(String id) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String message = now + " " + user + " " + "Get complaints complained to complainee (id: " + id + ")" ;
        systemLogService.writeToFile(message);

        return complaintRepository.findAllByComplaineeId(id);
    }
}
