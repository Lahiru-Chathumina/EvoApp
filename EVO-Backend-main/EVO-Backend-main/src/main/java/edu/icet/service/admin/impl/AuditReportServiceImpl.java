package edu.icet.service.admin.impl;

import edu.icet.dto.admin.AuditReport;
import edu.icet.entity.admin.AuditReportEntity;
import edu.icet.repository.admin.AuditReportRepository;
import edu.icet.service.admin.AuditReportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditReportServiceImpl implements AuditReportService {

    private  final AuditReportRepository auditReportRepository;
    private final ModelMapper modelMapper;
    @Override
    public boolean saveAuditReport(AuditReport auditReport) {

    if (auditReport==null){
        return false;
    }
    AuditReportEntity auditReportEntity=modelMapper.map(auditReport, AuditReportEntity.class);
        if (auditReportEntity==null){
            return false;
        }

    auditReportRepository.save(auditReportEntity);
        return auditReportRepository.existsById(auditReportEntity.getReportId());

    }

    @Override
    public AuditReport getAuditReportById(Long id) {

    return modelMapper.map(auditReportRepository.findById(id), AuditReport.class);

    }

    @Override
    public List<AuditReport> getAllAuditReports() {
    List<AuditReport>auditReportList=new ArrayList<>();
    List<AuditReportEntity> all= auditReportRepository.findAll();
    all.forEach(AuditReportEntity-> {

        auditReportList.add(modelMapper.map(AuditReportEntity, AuditReport.class));
    });


    return auditReportList;

    }

    @Override
    public boolean updateAuditReport(Long id, AuditReport auditReport) {
        if (id==null || auditReport==null){
            return false;

        }
        if (!auditReportRepository.existsById(id)){
            return false;
        }
        AuditReportEntity reportEntity=modelMapper.map(auditReport, AuditReportEntity.class);
        auditReportRepository.save(reportEntity);
        return  true;

    }

    @Override
    public boolean deleteAuditReportById(Long id) {
        if (auditReportRepository.existsById(id)){
            auditReportRepository.deleteById(id);
            return  true;
        }
        return  false;
    }

}
