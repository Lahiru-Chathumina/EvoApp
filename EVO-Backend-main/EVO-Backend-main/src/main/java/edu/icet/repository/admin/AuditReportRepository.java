package edu.icet.repository.admin;

import edu.icet.entity.admin.AuditReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditReportRepository extends JpaRepository<AuditReportEntity,Long> {
}
