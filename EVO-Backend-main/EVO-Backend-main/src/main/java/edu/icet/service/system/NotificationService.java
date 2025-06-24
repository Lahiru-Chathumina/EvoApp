package edu.icet.service.system;

import edu.icet.dto.system.Notification;

import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);
    Notification getNotificationById(Long notificationId);
    Notification updateNotification(Long notificationId, Notification notification);
    boolean deleteNotification(Long notificationId);
    List<Notification> getAllNotification();
    List<Notification> getNotificationByType(String type);
    List<Notification> getNotificationByStatus(String status);
    List<Notification> getNotificationByDeliveryMethod(String deliveryMethod);
    List<Notification>getNotificationByUser(String userType,Long userId);
    boolean markAsRead(Long notificationId);
    List<Notification> getUnreadNotifications();
}












