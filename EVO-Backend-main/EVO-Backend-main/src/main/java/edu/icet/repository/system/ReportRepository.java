package edu.icet.repository.system;

import edu.icet.entity.system.ReportEntity;
import edu.icet.util.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity,Long> {
    Iterable<Object> findAllByReportType(ReportType reportType);
}
