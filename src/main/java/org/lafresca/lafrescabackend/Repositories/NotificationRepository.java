package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> getAllNotificationsById(String userId);
}
