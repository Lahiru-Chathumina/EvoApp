package edu.icet.service.admin.impl;

import edu.icet.dto.admin.AuditHistory;
import edu.icet.entity.admin.AuditHistoryEntity;
import edu.icet.repository.admin.AuditHistoryRepository;
import edu.icet.service.admin.AuditHistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AuditHistoryServiceImpl implements AuditHistoryService {

    private  final AuditHistoryRepository auditHistoryRepository;
    private  final ModelMapper modelMapper;
    @Override
    public Boolean saveAuditHistory(AuditHistory auditHistory) {

     if (auditHistory==null){
         return  false;
     }
     AuditHistoryEntity auditHistoryEntity=modelMapper.map(auditHistory,AuditHistoryEntity.class);
     auditHistoryRepository.save(auditHistoryEntity);
     return auditHistoryRepository.existsById(auditHistoryEntity.getId());
    }

    @Override
    public List<AuditHistory> getAll() {
        List<AuditHistory>auditHistories=new ArrayList<>();
        List<AuditHistoryEntity> all= auditHistoryRepository.findAll();
        all.forEach(AuditHistoryEntity->{
            auditHistories.add(modelMapper.map(AuditHistoryEntity,AuditHistory.class));
        });
        return auditHistories;
    }

    @Override
    public AuditHistory findById(Long id) {
        return modelMapper.map(auditHistoryRepository.findById(id),AuditHistory.class);
    }

    @Override
    public Boolean deleteAuditHistory(Long id) {
        if (auditHistoryRepository.existsById(id)){
          auditHistoryRepository.deleteById(id);
          return true;
      }
      return false;
    }
}
