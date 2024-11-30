package org.lafresca.lafrescabackend.Services;

import lombok.extern.slf4j.Slf4j;
import org.lafresca.lafrescabackend.Models.Notification;
import org.lafresca.lafrescabackend.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final SystemLogService systemLogService;

    public NotificationService(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    public Notification sendNotification(String userId, String message) {
        Notification notification = new Notification();
        notification.setReceiverId(userId);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        Notification savedNotification = notificationRepository.save(notification);

        // Send to WebSocket topic
        messagingTemplate.convertAndSend("/topic/notifications/" + userId, notification);

        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Send notifications (id: " + savedNotification.getId() + ")";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);
        return notification;
    }

    public List<Notification> getUnreadNotifications(String userId) {
        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Retrieved unread notifications related to user id (id: " + userId + ")";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);

        return notificationRepository.getAllNotificationsById(userId);
    }

    public void markAsRead(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setRead(true);
        notificationRepository.save(notification);

        String user= SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + user + " " + "Notification marked as read (id: " + notificationId + ")";
        systemLogService.writeToFile(logmessage);
        log.info(logmessage);
    }
}
