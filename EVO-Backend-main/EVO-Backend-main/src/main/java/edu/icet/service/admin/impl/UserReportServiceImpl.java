package edu.icet.service.admin.impl;


import edu.icet.dto.customer.UserReport;
import edu.icet.service.admin.UserReportService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserReportServiceImpl implements UserReportService {
    List<UserReport> userReportList =new ArrayList<>();


    @Override
    public boolean saveUserReport(UserReport userReport) {
        if (userReport == null) {
            return false;
        } else {
            return userReportList.add(userReport);

        }
    }


    @Override
    public UserReport getUserReportById(Long id) {
        for (UserReport report : userReportList) {
            if (report.getReportId().equals(id)) {
                return report;
            }
        }
        return null;
    }

    @Override
    public List<UserReport> getAllUserReports() {
        if(userReportList!=null){
            return new ArrayList<>(userReportList);
        }
        return null;
    }

    @Override
    public boolean updateUserReport(Long id, UserReport userReport) {
        for (int i = 0; i < userReportList.size(); i++) {
            if (userReportList.get(i).getReportId().equals(id)) {
                userReportList.set(i, userReport);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUserReportById(Long id) {
        if (id == null) {
            return false;
        } else {
            return userReportList.removeIf(userReport -> Objects.equals(userReport.getReportId(), id));
        }
    }
}
