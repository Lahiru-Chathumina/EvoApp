package edu.icet.repository.event.impl;

import edu.icet.entity.event.GetTogetherEntity;
import edu.icet.repository.event.GetTogetherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
@RequiredArgsConstructor
@Transactional
public class GetTogetherRepositoryImpl implements GetTogetherRepository {
    private final Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public GetTogetherEntity add (GetTogetherEntity entity) {
        try {
            entityManager.persist(entity);
            return entity;
        } catch (Exception e) {
            logger.error("Error adding GetTogetherEntity: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public GetTogetherEntity update (GetTogetherEntity entity) {
        try {
            return entityManager.merge(entity);
        } catch (Exception e) {
            logger.error("Error updating GetTogetherEntity: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public GetTogetherEntity getByEventId (Long eventId) {
        try {
            return entityManager.createQuery(
                    "SELECT g FROM GetTogetherEntity g WHERE g.eventId = :eventId", GetTogetherEntity.class)
                .setParameter("eventId", eventId)
                .getResultStream()
                .findFirst()
                .orElse(null);
        } catch (Exception e) {
            logger.error("Error getting GetTogetherEntity by eventId: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public GetTogetherEntity getByEventSummaryId (Long eventSummaryId) {
        try {
            return entityManager.createQuery(
                    "SELECT g FROM GetTogetherEntity g WHERE g.eventSummaryId = :eventSummaryId", GetTogetherEntity.class)
                .setParameter("eventSummaryId", eventSummaryId)
                .getResultStream()
                .findFirst()
                .orElse(null);
        } catch (Exception e) {
            logger.error("Error getting GetTogetherEntity by eventSummaryId: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean setEventId (Long eventSummaryId, Long eventId) {
        try {
            int updated = entityManager.createQuery(
                    "UPDATE GetTogetherEntity g SET g.eventId = :eventId WHERE g.eventSummaryId = :eventSummaryId")
                .setParameter("eventId", eventId)
                .setParameter("eventSummaryId", eventSummaryId)
                .executeUpdate();
            return updated > 0;
        } catch (Exception e) {
            logger.error("Error setting eventId for GetTogetherEntity: {}", e.getMessage(), e);
            return false;
        }
    }
}
