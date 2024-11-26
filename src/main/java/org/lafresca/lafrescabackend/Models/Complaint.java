package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Complaint")
@Data
public class Complaint {
    @Id
    private String id;
    private String Complainee;  //branch id
    private String Complainer;
    private String Topic;
    private String Description;
    private LocalDateTime Date;
}