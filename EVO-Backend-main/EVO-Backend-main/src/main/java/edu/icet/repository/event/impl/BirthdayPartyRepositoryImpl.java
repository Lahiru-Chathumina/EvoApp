package edu.icet.repository.event.impl;

import edu.icet.entity.event.BirthdayPartyEntity;
import edu.icet.repository.event.BirthdayPartyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
@RequiredArgsConstructor
public class BirthdayPartyRepositoryImpl implements BirthdayPartyRepository {
    private final Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BirthdayPartyEntity add (BirthdayPartyEntity entity) {
        try {
            Query query = entityManager.createNativeQuery("""
                INSERT INTO birthday_party (event_id, event_summary_id, owner_name)
                VALUES (:event_id, :event_summary_id, :owner_name)
            """);
            query.setParameter("event_id", entity.getEventId());
            query.setParameter("event_summary_id", entity.getEventSummaryId());
            query.setParameter("owner_name", entity.getOwnerName());

            int inserted = query.executeUpdate();
            return inserted == 0 ? null : entity;
        } catch (Exception e) {
            logger.error("Error inserting birthday party: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public BirthdayPartyEntity update (BirthdayPartyEntity entity) {
        try {
            Query query = entityManager.createNativeQuery("""
                UPDATE birthday_party
                SET event_id = :new_event_id, event_summary_id = :new_event_summary_id, owner_name = :owner_name
                WHERE event_id = :event_id OR event_summary_id = :event_summary_id
            """);
            query.setParameter("new_event_id", entity.getEventId());
            query.setParameter("new_event_summary_id", entity.getEventSummaryId());
            query.setParameter("owner_name", entity.getOwnerName());
            query.setParameter("event_id", entity.getEventId());
            query.setParameter("event_summary_id", entity.getEventSummaryId());

            int updated = query.executeUpdate();
            return updated == 0 ? null : entity;
        } catch (Exception e) {
            logger.error("Error updating birthday party: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public BirthdayPartyEntity getByEventId (Long eventId) {
        try {
            Object[] result = (Object[]) entityManager.createNativeQuery("""
                SELECT event_summary_id, owner_name FROM birthday_party WHERE event_id = :event_id
            """)
                .setParameter("event_id", eventId)
                .getSingleResult();

            return BirthdayPartyEntity.builder()
                .eventId(eventId)
                .eventSummaryId(((Number) result[0]).longValue())
                .ownerName((String) result[1])
                .build();
        } catch (Exception e) {
            logger.error("Error fetching by event ID: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public BirthdayPartyEntity getByEventSummaryId (Long eventSummaryId) {
        try {
            Object[] result = (Object[]) entityManager.createNativeQuery("""
                SELECT event_id, owner_name FROM birthday_party WHERE event_summary_id = :event_summary_id
            """)
                .setParameter("event_summary_id", eventSummaryId)
                .getSingleResult();

            return BirthdayPartyEntity.builder()
                .eventSummaryId(eventSummaryId)
                .eventId(((Number) result[0]).longValue())
                .ownerName((String) result[1])
                .build();
        } catch (Exception e) {
            logger.error("Error fetching by event summary ID: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean setEventId (Long eventSummaryId, Long eventId) {
        try {
            int updated = entityManager.createNativeQuery("""
                UPDATE birthday_party SET event_id = :event_id WHERE event_summary_id = :event_summary_id
            """)
                .setParameter("event_id", eventId)
                .setParameter("event_summary_id", eventSummaryId)
                .executeUpdate();

            return updated == 0;
        } catch (Exception e) {
            logger.error("Error setting event ID: {}", e.getMessage());
            return false;
        }
    }
}
