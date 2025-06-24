package edu.icet.service.admin;

import edu.icet.dto.system.TransactionHistory;

import java.time.LocalDate;
import java.util.List;

public interface TransactionHistoryService {
    Boolean saveTransactionHistory(TransactionHistory history);
    TransactionHistory getTransactionHistoryById(Long id);
    List<TransactionHistory> getAll();
    boolean deleteTransactionHistoryById(Long id);
    List<TransactionHistory> getTransactionByDate(LocalDate date);
    List<TransactionHistory> getTransactionByUserId(Long id);
}
