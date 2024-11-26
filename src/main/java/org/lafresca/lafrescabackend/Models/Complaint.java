package org.lafresca.lafrescabackend.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Complaint")
@Data
public class Complaint {
    @Id
    private String id;

    @NotEmpty(message = "Complainee cannot be empty")
    @NotNull(message = "Complainee cannot be null")
    private String Complainee;  //branch id

    @NotEmpty(message = "Complainer cannot be empty")
    @NotNull(message = "Complainer cannot be null")
    private String Complainer;

    @NotEmpty(message = "Topic cannot be empty")
    @NotNull(message = "Topic cannot be null")
    private String Topic;

    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description cannot be null")
    private String Description;

    private LocalDateTime Date;
}