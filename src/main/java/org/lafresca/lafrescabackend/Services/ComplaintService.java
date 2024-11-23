package org.lafresca.lafrescabackend.Services;

import org.lafresca.lafrescabackend.Models.Complaint;
import org.lafresca.lafrescabackend.Repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public String addNewComplaint(Complaint complaint) {

        try {
            complaint.setDate(LocalDateTime.now());
            Complaint newComplaint = complaintRepository.save(complaint);
            return newComplaint.toString();
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Error saving complaint", e);
        }

    }
}
