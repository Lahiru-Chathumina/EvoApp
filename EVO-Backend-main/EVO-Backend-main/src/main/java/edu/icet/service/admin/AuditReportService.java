package edu.icet.service.admin;

import edu.icet.dto.admin.AuditReport;
import java.util.List;

public interface AuditReportService {
    boolean saveAuditReport(AuditReport auditReport);
    AuditReport getAuditReportById(Long id);
    List<AuditReport> getAllAuditReports();
    boolean updateAuditReport(Long id, AuditReport auditReport);
    boolean deleteAuditReportById(Long id);
}
