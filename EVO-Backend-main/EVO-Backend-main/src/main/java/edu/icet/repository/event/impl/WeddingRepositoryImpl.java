package edu.icet.repository.event.impl;

import edu.icet.entity.event.WeddingEntity;
import edu.icet.repository.event.WeddingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Repository
@RequiredArgsConstructor
public class WeddingRepositoryImpl implements WeddingRepository {
    private final Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public WeddingEntity add (WeddingEntity entity) {
        try {
            entityManager.persist(entity);
            return entity;
        } catch (Exception e) {
            logger.error("Failed to persist WeddingEntity: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    @Transactional
    public WeddingEntity update (WeddingEntity entity) {
        try {
            return entityManager.merge(entity);
        } catch (Exception e) {
            logger.error("Failed to update WeddingEntity: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public WeddingEntity getByEventId (Long eventId) {
        try {
            return entityManager
                .createQuery("SELECT w FROM WeddingEntity w WHERE w.eventId = :eventId", WeddingEntity.class)
                .setParameter("eventId", eventId)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            logger.error("Error fetching by eventId: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public WeddingEntity getByEventSummaryId (Long eventSummaryId) {
        try {
            return entityManager
                .createQuery("SELECT w FROM WeddingEntity w WHERE w.eventSummaryId = :eventSummaryId", WeddingEntity.class)
                .setParameter("eventSummaryId", eventSummaryId)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            logger.error("Error fetching by eventSummaryId: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    @Transactional
    public boolean setEventId (Long eventSummaryId, Long eventId) {
        try {
            int updated = entityManager.createQuery(
                    "UPDATE WeddingEntity w SET w.eventId = :eventId WHERE w.eventSummaryId = :eventSummaryId")
                .setParameter("eventId", eventId)
                .setParameter("eventSummaryId", eventSummaryId)
                .executeUpdate();
            return updated > 0;
        } catch (Exception e) {
            logger.error("Error updating eventId: {}", e.getMessage(), e);
            return false;
        }
    }
}
