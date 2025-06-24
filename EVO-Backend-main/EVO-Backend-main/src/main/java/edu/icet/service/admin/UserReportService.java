package edu.icet.service.admin;

import edu.icet.dto.customer.UserReport;
import java.util.List;

public interface UserReportService {
    boolean saveUserReport(UserReport userReport);
    UserReport getUserReportById(Long id);
    List<UserReport> getAllUserReports();
    boolean updateUserReport(Long id, UserReport userReport);
    boolean deleteUserReportById(Long id);
}
