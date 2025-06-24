package edu.icet.service.admin.impl;

import edu.icet.dto.admin.AuditLog;
import edu.icet.entity.admin.AuditLogEntity;
import edu.icet.repository.admin.AuditLogRepository;
import edu.icet.service.admin.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository repository;
    final ModelMapper mapper;

    @Override
    public boolean saveAuditLog(AuditLog auditLog) {
        if(auditLog == null) {
            return false;
        }
        repository.save(mapper.map(auditLog, AuditLogEntity.class));
        return true;
    }

    @Override
    public AuditLog getAuditLogById(Long id) {
        if (id == null){
            return null;
        }
        if (!repository.existsById(id)){
            return null;
        }
        return mapper.map(repository.findById(id), AuditLog.class);
    }

    @Override
    public List<AuditLog> getAllAuditLogs() {
        List<AuditLog> auditLogList = new ArrayList<>();
        List<AuditLogEntity> all = repository.findAll();

        all.forEach(auditLogEntity -> {
            auditLogList.add(mapper.map(auditLogEntity, AuditLog.class));
        });

        return auditLogList;
    }

    @Override
    public boolean deleteAuditLog(Long id) {
        if (id == null){
            return false;
        }
        if (!repository.existsById(id)){
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
