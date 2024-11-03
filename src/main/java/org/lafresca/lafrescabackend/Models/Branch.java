package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Branch")
@Data
public class Branch {
    @Id
    private String id;
    private String Name;
    private String Address;
    private String ContactNo;
    private Double Longitude;
    private Double Latitude;
    private String BranchManager;
    private Integer Deleted;
    private BranchStatus Status;
}
