package edu.icet.repository.event.impl;

import edu.icet.entity.event.AnniversaryEntity;
import edu.icet.repository.event.AnniversaryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
@RequiredArgsConstructor
public class AnniversaryRepositoryImpl implements AnniversaryRepository {
    private final Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AnniversaryEntity add (AnniversaryEntity entity) {
        try {
            final Query insertQuery = this.entityManager.createNativeQuery("""
                INSERT INTO anniversary (event_id, event_summary_id, anniversary_year, wife_name, husband_name, description)
                VALUES (:event_id, :event_summary_id, :anniversary_year, :wife_name, :husband_name, :description)
            """);

            insertQuery.setParameter("event_id", entity.getEventId());
            insertQuery.setParameter("event_summary_id", entity.getEventSummaryId());
            insertQuery.setParameter("anniversary_year", entity.getAnniversaryYear());
            insertQuery.setParameter("wife_name", entity.getWifeName());
            insertQuery.setParameter("husband_name", entity.getHusbandName());
            insertQuery.setParameter("description", entity.getDescription());

            insertQuery.executeUpdate();

            return entity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return null;
        }
    }

    @Override
    public AnniversaryEntity update (AnniversaryEntity entity) {
        try {
            final Query updateQuery = this.entityManager.createNativeQuery("""
                UPDATE anniversary
                SET event_id = :new_event_id,
                    event_summary_id = :new_event_summary_id,
                    anniversary_year = :anniversary_year,
                    wife_name = :wife_name,
                    husband_name = :husband_name,
                    description = :description
                WHERE event_id = :event_id OR event_summary_id = :event_summary_id
            """);

            updateQuery.setParameter("new_event_id", entity.getEventId());
            updateQuery.setParameter("new_event_summary_id", entity.getEventSummaryId());
            updateQuery.setParameter("anniversary_year", entity.getAnniversaryYear());
            updateQuery.setParameter("wife_name", entity.getWifeName());
            updateQuery.setParameter("husband_name", entity.getHusbandName());
            updateQuery.setParameter("description", entity.getDescription());
            updateQuery.setParameter("event_id", entity.getEventId());
            updateQuery.setParameter("event_summary_id", entity.getEventSummaryId());

            updateQuery.executeUpdate();

            return entity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return null;
        }
    }

    @Override
    public AnniversaryEntity getByEventId (Long eventId) {
        try {
            TypedQuery<Object[]> query = (TypedQuery<Object[]>) this.entityManager.createNativeQuery("""
                SELECT event_summary_id, anniversary_year, wife_name, husband_name, description
                FROM anniversary
                WHERE event_id = :event_id
            """, Object[].class);

            query.setParameter("event_id", eventId);
            Object[] row = query.getSingleResult();

            return AnniversaryEntity.builder()
                .eventId(eventId)
                .eventSummaryId(((Number) row[0]).longValue())
                .anniversaryYear((Integer) row[1])
                .wifeName((String) row[2])
                .husbandName((String) row[3])
                .description((String) row[4])
                .build();
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return null;
        }
    }

    @Override
    public AnniversaryEntity getByEventSummaryId (Long eventSummaryId) {
        try {
            TypedQuery<Object[]> query = (TypedQuery<Object[]>) this.entityManager.createNativeQuery("""
                SELECT event_id, anniversary_year, wife_name, husband_name, description
                FROM anniversary
                WHERE event_summary_id = :event_summary_id
            """, Object[].class);

            query.setParameter("event_summary_id", eventSummaryId);
            Object[] row = query.getSingleResult();

            return AnniversaryEntity.builder()
                .eventSummaryId(eventSummaryId)
                .eventId(((Number) row[0]).longValue())
                .anniversaryYear((Integer) row[1])
                .wifeName((String) row[2])
                .husbandName((String) row[3])
                .description((String) row[4])
                .build();
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean setEventId (Long eventSummaryId, Long eventId) {
        try {
            Query query = this.entityManager.createNativeQuery("""
                UPDATE anniversary SET event_id = :event_id WHERE event_summary_id = :event_summary_id
            """);
            query.setParameter("event_id", eventId);
            query.setParameter("event_summary_id", eventSummaryId);

            int rowsAffected = query.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return false;
        }
    }
}
