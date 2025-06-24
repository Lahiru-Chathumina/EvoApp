package edu.icet.repository.system;

import edu.icet.entity.system.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {
    List<NotificationEntity> findByType(String type);

    List<NotificationEntity> findByStatus(String status);

    List<NotificationEntity> findByDeliveryMethod(String deliveryMethod);

    List<NotificationEntity> findByUserTypeAndUserId(String userType, Long userId);

    List<NotificationEntity> findByReadAtIsNull();
}
