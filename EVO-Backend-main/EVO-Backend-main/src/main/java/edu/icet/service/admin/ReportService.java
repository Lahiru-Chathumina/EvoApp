package edu.icet.service.admin;

import edu.icet.dto.admin.Report;
import edu.icet.util.ReportType;
import jakarta.validation.Valid;

import java.util.List;

public interface ReportService {
    boolean saveReport(@Valid Report report);

    boolean updateReport(Long reportId, @Valid Report report);

    boolean deleteReportById(Long reportId);

    Report searchReport(Long reportId);

    List<Report> getAllReports();

    List<Report> getFillterReports(ReportType reportType);
}
