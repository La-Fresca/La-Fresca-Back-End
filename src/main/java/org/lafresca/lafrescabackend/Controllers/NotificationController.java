package org.lafresca.lafrescabackend.Controllers;

import org.lafresca.lafrescabackend.Models.Notification;
import org.lafresca.lafrescabackend.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public Notification sendNotification(@RequestParam String userId, @RequestParam String message) {
        return notificationService.sendNotification(userId, message);
    }

    @GetMapping("/unread/{userId}")
    public List<Notification> getUnreadNotifications(@PathVariable String userId) {
        return notificationService.getUnreadNotifications(userId);
    }

    @PutMapping("/mark-read/{notificationId}")
    public void markNotificationAsRead(@PathVariable String notificationId) {
        notificationService.markAsRead(notificationId);
    }
}

