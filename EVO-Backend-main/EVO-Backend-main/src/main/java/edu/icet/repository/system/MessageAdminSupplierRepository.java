package edu.icet.repository.system;

import edu.icet.entity.system.MessageAdminSupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageAdminSupplierRepository extends JpaRepository<MessageAdminSupplierEntity,Long> {
    List<MessageAdminSupplierEntity> findBySupplierId(Long supplierId);

    List<MessageAdminSupplierEntity> findByAdminIdAndSupplierId(Long adminId, Long supplierId);

    List<MessageAdminSupplierEntity> findByAdminId(Long adminId);
}
