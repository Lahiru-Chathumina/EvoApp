package edu.icet.repository.system;

import edu.icet.entity.admin.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryEntity,Long> {
}
