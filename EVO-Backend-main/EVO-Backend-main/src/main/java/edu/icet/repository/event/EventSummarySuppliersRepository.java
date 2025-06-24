package edu.icet.repository.event;

import edu.icet.entity.event.EventSummaryEntity;
import edu.icet.entity.event.EventSummarySuppliersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventSummarySuppliersRepository extends JpaRepository<EventSummarySuppliersEntity, Long> {
    EventSummarySuppliersEntity findByEventSummary(EventSummaryEntity eventSummary);

}
