package edu.icet.repository.admin;

import edu.icet.entity.admin.AuditHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditHistoryRepository extends JpaRepository<AuditHistoryEntity,Long> {
}
