package edu.icet.controller.customer;

import edu.icet.dto.customer.UserReport;
import edu.icet.service.admin.UserReportService;
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
@RequestMapping("/user-report")
@RequiredArgsConstructor
public class UserReportController {

    private final UserReportService userReportService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUserReport(@Valid @RequestBody UserReport userReport, HttpServletRequest request) {
        if (userReportService.saveUserReport(userReport)) {
            String ip = request.getRemoteAddr();
            log.info("Request Received IP: {} | Added UserReport: {}", ip, userReport);
            return ResponseEntity.ok("User report saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Try again! Cannot add user report.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserReport(@PathVariable Long id, @Valid @RequestBody UserReport userReport, HttpServletRequest request) {
        if (userReportService.updateUserReport(id, userReport)) {
            String ip = request.getRemoteAddr();
            log.info("Request Received IP: {} | Updated UserReport: {}", ip, userReport);
            return ResponseEntity.ok("User report updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Try again! Cannot update user report.");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserReport> getUserReportById(@PathVariable Long id) {
        UserReport userReport = userReportService.getUserReportById(id);
        return (userReport != null) ? ResponseEntity.ok(userReport) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserReport>> getAllUserReports() {
        List<UserReport> userReports = userReportService.getAllUserReports();
        return (!userReports.isEmpty()) ? ResponseEntity.ok(userReports) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserReportById(@PathVariable Long id, HttpServletRequest request) {
        if (userReportService.deleteUserReportById(id)) {
            String ip = request.getRemoteAddr();
            log.info("Request Received IP: {} | Deleted UserReport ID: {}", ip, id);
            return ResponseEntity.ok("User report deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Try again! Cannot delete user report.");
        }
    }
}
