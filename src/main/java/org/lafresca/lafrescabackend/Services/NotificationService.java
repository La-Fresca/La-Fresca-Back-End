package org.lafresca.lafrescabackend.Services;

import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.Models.Notification;
import org.lafresca.lafrescabackend.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Notification sendNotification(String userId, String message) {
        Notification notification = new Notification();
        notification.setReceiverId(userId);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);

        // Send to WebSocket topic
        messagingTemplate.convertAndSend("/topic/notifications/" + userId, notification);
        return notification;
    }

    public List<Notification> getUnreadNotifications(String userId) {
        return notificationRepository.getAllNotificationsById(userId);
    }

    public void markAsRead(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
