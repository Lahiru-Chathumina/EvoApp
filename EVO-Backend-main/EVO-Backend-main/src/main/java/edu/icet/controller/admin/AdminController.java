package edu.icet.controller.admin;

import edu.icet.dto.admin.Admin;
import edu.icet.dto.admin.AuditHistory;
import edu.icet.dto.admin.AuditLog;
import edu.icet.dto.admin.AuditReport;
import edu.icet.service.admin.AdminService;
import edu.icet.service.admin.AuditHistoryService;
import edu.icet.service.admin.AuditLogService;
import edu.icet.service.admin.AuditReportService;
import edu.icet.util.AdminType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class AdminController {

    private final AdminService adminService;
    final AuditHistoryService service;
    final AuditLogService auditLogService;
    final AuditReportService reportService;

    @PostMapping("/add")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
        boolean addAdmin = adminService.addAdmin(admin);
        if (addAdmin) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long adminId) {
        boolean deleteAdmin = adminService.deleteAdmin(adminId);
        if (deleteAdmin) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{adminId}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long adminId, @RequestBody Admin admin) {
        boolean updateAdmin = adminService.updateAdmin(adminId, admin);
        if (updateAdmin) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId) {
        Admin admin = adminService.getAdminById(adminId);
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/exists/{adminId}")
    public ResponseEntity<String> adminExits(@PathVariable Long adminId) {
        boolean existsAdmin = adminService.adminExists(adminId);
        if (existsAdmin) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAdmins() {
        Long adminCount = adminService.countAdmins();
        if (adminCount > 0) {
            return new ResponseEntity<>(adminCount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> allAdmins = adminService.getAllAdmins();
        if (allAdmins != null) {
            return new ResponseEntity<>(allAdmins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-type/{type}")
    public ResponseEntity<List<Admin>> getAdminByType(@PathVariable("type") String type) {
        List<Admin> adminsByType = adminService.getAdminByType(AdminType.valueOf(type));
        if (adminsByType != null && !adminsByType.isEmpty()) {
            return new ResponseEntity<>(adminsByType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/audit/history/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Boolean> saveAuditHistory(@RequestBody AuditHistory auditHistory) {
        Boolean result = service.saveAuditHistory(auditHistory);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/audit/history/get-all")
    ResponseEntity<List<AuditHistory>> getAll() {
        List<AuditHistory> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/audit/history/find-by-id/{id}")
    ResponseEntity<AuditHistory> findById(@PathVariable Long id) {
        AuditHistory byId = service.findById(id);

        if (byId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId);
    }

    @DeleteMapping("/audit/history/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<Boolean> deleteAuditHistory(@PathVariable Long id) {
        Boolean result = service.deleteAuditHistory(id);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @PostMapping("/audit/log/save")
    public ResponseEntity<String> saveAuditLog(@RequestBody AuditLog auditLog) {
        boolean isSaved = auditLogService.saveAuditLog(auditLog);
        if (isSaved) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Audit log saved successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the audit log.");
        }
    }

    @GetMapping("/audit/log/get-by-id/{logId}")
    public ResponseEntity<AuditLog> getAuditLogById(@PathVariable Long logId) {
        AuditLog auditLog = auditLogService.getAuditLogById(logId);
        return auditLog != null ? ResponseEntity.ok(auditLog) : ResponseEntity.notFound().build();
    }

    @GetMapping("/audit/log/get-all")
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        List<AuditLog> auditLogList = auditLogService.getAllAuditLogs();
        return auditLogList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(auditLogList);
    }

    @DeleteMapping("/audit/log/delete/{logId}")
    public ResponseEntity<String> deleteAuditLog(@PathVariable Long logId) {
        boolean isDeleted = auditLogService.deleteAuditLog(logId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Audit log deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Audit log not found.");
        }
    }

    @PostMapping("/audit/report/add")
    public ResponseEntity<String> addAuditReport(@Valid @RequestBody AuditReport auditReport, HttpServletRequest request) {
        if (reportService.saveAuditReport(auditReport)) {
            String os = request.getRemoteAddr();
            log.info("Request Received IP: {} | Add Report detail: {}", os, auditReport);
            return ResponseEntity.ok(" Report saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("try again!, Can't added Report");
        }
    }

    @PutMapping("/audit/report/update/{reportId}")
    public ResponseEntity<String> updateReport(@PathVariable Long reportId, @Valid @RequestBody AuditReport auditReport) {
        if (reportService.updateAuditReport(reportId, auditReport)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/audit/report/delete")
    public ResponseEntity<String> deleteReport(@PathVariable Long reportId) {
        if (reportService.deleteAuditReportById(reportId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/audit/report/search/{reportId}")
    public ResponseEntity<AuditReport> searchReport(@PathVariable Long reportId) {
        AuditReport auditReport = reportService.getAuditReportById(reportId);
        if (auditReport != null) {
            return new ResponseEntity<>(auditReport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/audit/report/getAll")
    public ResponseEntity<List<AuditReport>> getAllReport() {
        List<AuditReport> auditReports = reportService.getAllAuditReports();
        if (auditReports != null) {
            return new ResponseEntity<>(auditReports, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
