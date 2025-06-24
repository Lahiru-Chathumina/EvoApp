package edu.icet.repository.system;

import edu.icet.entity.system.SearchHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistoryEntity,Long> {
    List<SearchHistoryEntity> findByTitle(String title);

/*    @Modifying
    @Transactional
    @Query("DELETE FROM SearchHistory sh WHERE sh.id IN (SELECT s.id FROM Customer c JOIN c.searchHistories s WHERE c.id = :customerId)")
    void deleteByCustomerId(@Param("customerId") Long customerId);*/

}
