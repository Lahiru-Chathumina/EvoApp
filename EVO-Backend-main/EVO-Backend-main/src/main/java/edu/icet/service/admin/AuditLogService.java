package edu.icet.service.admin;

import edu.icet.dto.admin.AuditLog;

import java.util.List;

public interface AuditLogService {
   boolean saveAuditLog(AuditLog auditLog);
   AuditLog getAuditLogById(Long logId);
   List<AuditLog> getAllAuditLogs();
   boolean deleteAuditLog(Long logId);
}
