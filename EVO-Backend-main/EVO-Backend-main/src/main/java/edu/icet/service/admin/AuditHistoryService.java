package edu.icet.service.admin;

import edu.icet.dto.admin.AuditHistory;

import java.util.List;

public interface AuditHistoryService {
    Boolean saveAuditHistory(AuditHistory auditHistory);
    List<AuditHistory> getAll();
    AuditHistory findById(Long id);
    Boolean deleteAuditHistory(Long id);


}
