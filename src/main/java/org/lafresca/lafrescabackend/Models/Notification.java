package org.lafresca.lafrescabackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Notification")
@Data
public class Notification {
    @Id
    private String id;
    private String senderId;
    private String receiverId;
    private String message;
    private LocalDateTime timestamp;
    private boolean isRead = false;
}
