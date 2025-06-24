package edu.icet.service.admin.impl;

import edu.icet.dto.system.TransactionHistory;
import edu.icet.entity.admin.TransactionHistoryEntity;
import edu.icet.repository.system.TransactionHistoryRepository;
import edu.icet.service.admin.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    private final TransactionHistoryRepository repository;
    private final ModelMapper mapper;

    @Override
    public Boolean saveTransactionHistory(TransactionHistory history) {
        if (history == null) {
            return false;
        }
        repository.save(mapper.map(history, TransactionHistoryEntity.class));
        return true;
    }

    @Override
    public TransactionHistory getTransactionHistoryById(Long id) {
        if (id == null){
            return null;
        }
        if (!repository.existsById(id)){
            return null;
        }
        return mapper.map(repository.findById(id), TransactionHistory.class);
    }

    @Override
    public List<TransactionHistory> getAll() {
        List<TransactionHistory> transactionHistoryList = new ArrayList<>();
        List<TransactionHistoryEntity> all = repository.findAll();

        all.forEach(transactionHistoryEntity -> {
            transactionHistoryList.add(mapper.map(transactionHistoryEntity, TransactionHistory.class));
        });

        return transactionHistoryList;
    }

    @Override
    public boolean deleteTransactionHistoryById(Long id) {
        if (id == null){
            return false;
        }
        if (!repository.existsById(id)){
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<TransactionHistory> getTransactionByDate(LocalDate date) {
        List<TransactionHistory>result=new ArrayList<>();
        for (TransactionHistory transaction:this.getAll()){
            if (transaction.getDate().equals(date)){
                result.add(transaction);
            }
        }
        return result;
    }

    @Override
    public List<TransactionHistory> getTransactionByUserId(Long id) {
        List<TransactionHistory>result=new ArrayList<>();
        for (TransactionHistory transaction:this.getAll()){
            if (transaction.getUserId().equals(id)){
                result.add(transaction);
            }
        }
        return result;
    }
}
