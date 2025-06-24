package edu.icet.service.system.impl;

import edu.icet.dto.system.Notification;
import edu.icet.entity.system.NotificationEntity;
import edu.icet.repository.system.NotificationRepository;
import edu.icet.service.system.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

   private final NotificationRepository repository;
   private final ModelMapper mapper;

    @Override
    public Notification createNotification(@Valid Notification notification) {
        if (notification != null) {
            NotificationEntity saved=repository.save(mapper.map(notification,NotificationEntity.class));
            Notification savedNotification=mapper.map(saved,Notification.class);
            return savedNotification;
        }
        return null;
    }

    @Override
    public Notification getNotificationById(Long notificationId) {
        if (notificationId != null) {
           if (!repository.existsById(notificationId)){
               throw new RuntimeException("Notification"+notificationId+"not found!");
           }
           return repository.findById(notificationId).map(notificationEntity -> mapper.map(notificationEntity,Notification.class)).orElse(null);
        }
        return null;
    }

    @Override
    public Notification updateNotification(Long notificationId, Notification notification) {
        if (notificationId != null && notification != null) {
            if (!repository.existsById(notificationId)){
                throw new RuntimeException("Notification"+notificationId+"not found!");
            }
            NotificationEntity updatedEntity=repository.save(mapper.map(notification,NotificationEntity.class));
            return mapper.map(updatedEntity,Notification.class);
        }
        return null;
    }
    @Override
    public boolean deleteNotification(Long notificationId) {
        if (notificationId != null) {
          if (repository.existsById(notificationId)){
              repository.deleteById(notificationId);
          }
          return true;
        }
        return false;
    }
    @Override
    public List<Notification> getAllNotification() {
       List<NotificationEntity> notificationEntities=repository.findAll();
       if (!notificationEntities.isEmpty()){
           List<Notification> notificationList= new ArrayList<>();
           notificationEntities.forEach(notificationEntity ->
                   notificationList.add(mapper.map(notificationEntity,Notification.class)));
           return notificationList;
       }
       return null;
    }

    @Override
    public List<Notification> getNotificationByType(String type) {
        if (type != null) {
            List<NotificationEntity> entities = repository.findByType(type);
            List<Notification> notificationsByType = new ArrayList<>();
            entities.forEach(entity -> notificationsByType.add(mapper.map(entity, Notification.class)));
            return notificationsByType;
           }
        return null;
    }

    @Override
    public List<Notification> getNotificationByStatus(String status) {
        if (status != null) {
           List<NotificationEntity>entities=repository.findByStatus(status);
           List<Notification> notificationsByStatus=new ArrayList<>();
           entities.forEach(entity-> notificationsByStatus.add(mapper.map(entity,Notification.class)));
           return notificationsByStatus;
        }
        return null;
    }

    @Override
    public List<Notification> getNotificationByDeliveryMethod(String deliveryMethod) {
        if (deliveryMethod != null) {
            List<NotificationEntity>entities=repository.findByDeliveryMethod(deliveryMethod);
            List<Notification> notificationsByDeliveryMethod=new ArrayList<>();
            entities.forEach(entity->notificationsByDeliveryMethod.add(mapper.map(entity,Notification.class)));
            return notificationsByDeliveryMethod;
        }
        return null;
    }

    @Override
    public List<Notification> getNotificationByUser(String userType, Long userId) {
        List<NotificationEntity> entities = repository.findByUserTypeAndUserId(userType, userId);
        List<Notification> notificationsByUser = new ArrayList<>();
        entities.forEach(entity -> notificationsByUser.add(mapper.map(entity, Notification.class)));
        return notificationsByUser;
    }

    @Override
    public boolean markAsRead(Long notificationId) {
        if(notificationId!=null) {
            NotificationEntity entity = repository.findById(notificationId)
                    .orElseThrow(() -> new RuntimeException("Notification not found"));

            entity.setReadAt(LocalDate.now());
            repository.save(entity);
            return true;
        }return false;
    }

    @Override
    public List<Notification> getUnreadNotifications() {
        List<NotificationEntity> entities = repository.findByReadAtIsNull();
        List<Notification> unreadNotifications = new ArrayList<>();
        entities.forEach(entity -> unreadNotifications.add(mapper.map(entity, Notification.class)));
        return unreadNotifications;
    }
}
