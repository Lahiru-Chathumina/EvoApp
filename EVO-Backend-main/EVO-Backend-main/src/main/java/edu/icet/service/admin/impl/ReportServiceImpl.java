package edu.icet.service.admin.impl;

import edu.icet.dto.admin.Report;
import edu.icet.entity.system.ReportEntity;
import edu.icet.repository.system.ReportRepository;
import edu.icet.service.admin.ReportService;
import edu.icet.util.ReportType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ModelMapper mapper;

    private final ReportRepository repo;

    @Override
    public boolean saveReport(Report report) {
        ReportEntity save = repo.save(mapper.map(report, ReportEntity.class));
        if (save!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReport(Long reportId, Report report) {
       if (repo.existsById(reportId)){
              repo.save(mapper.map(report, ReportEntity.class));
              return true;
        }
       return false;
    }

    @Override
    public boolean deleteReportById(Long reportId) {
      if (repo.existsById(reportId)){
          repo.deleteById(reportId);
          return true;
      }
      return  false;
    }

    @Override
    public Report searchReport(Long reportId) {
     return  mapper.map(repo.findById(reportId), Report.class);
    }

    @Override
    public List<Report> getAllReports() {
        List<Report> reportList = new ArrayList<>();
        repo.findAll().forEach(report -> {
            reportList.add(mapper.map(report, Report.class));
        });
        return reportList;
    }

    @Override
    public List<Report> getFillterReports(ReportType reportType) {
        List<Report> filteredReports = new ArrayList<>();
        repo.findAllByReportType(reportType).forEach(report -> {
            filteredReports.add(mapper.map(report, Report.class));
        });
        return filteredReports;
    }
}
