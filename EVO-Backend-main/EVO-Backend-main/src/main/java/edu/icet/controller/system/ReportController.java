package edu.icet.controller.system;

import edu.icet.dto.admin.Report;
import edu.icet.service.admin.ReportService;
import edu.icet.util.ReportType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/system/report")
@RequiredArgsConstructor

public class ReportController {

    final ReportService reportService;

    @PostMapping("/add-report")
    public ResponseEntity<String> addReport(@Valid @RequestBody Report report, HttpServletRequest request) {
        if (reportService.saveReport(report)) {
            String os = request.getRemoteAddr();
            log.info("Request Received IP: {} | Add Report detail: {}", os, report);
            return ResponseEntity.ok(" Report saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("try again!, Can't added Report");
        }
    }


    @PutMapping("/update-report/{reportId}")
    public ResponseEntity<String> updateReport(@PathVariable Long reportId, @Valid @RequestBody Report report) {
        if (reportService.updateReport(reportId, report)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-report")
    public ResponseEntity<String> deleteReport(@PathVariable Long reportId) {
        if (reportService.deleteReportById(reportId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search-report-by-id/{reportId}")
    public ResponseEntity<Report> searchReport(@PathVariable Long reportId) {
        Report report = reportService.searchReport(reportId);
        if (report != null) {
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll-reports")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> allReports = reportService.getAllReports();
        if (allReports != null) {
            return new ResponseEntity<>(allReports, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filter-reports")
    public ResponseEntity<List<Report>> getFilterReports(ReportType reportType) {
        List<Report> filterReports = reportService.getFillterReports(reportType);
        if (filterReports != null) {
            return new ResponseEntity<>(filterReports, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
