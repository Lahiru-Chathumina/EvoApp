package edu.icet.repository.event.impl;

import edu.icet.entity.customer.UserEntity;
import edu.icet.entity.event.*;
import edu.icet.entity.supplier.VenueEntity;
import edu.icet.repository.customer.UserRepository;
import edu.icet.repository.event.*;
import edu.icet.repository.supplier.VenueRepository;
import edu.icet.util.BudgetType;
import edu.icet.util.EventStatusType;
import edu.icet.util.EventType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Primary
@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {
    private final Logger logger;
    private final UserRepository userRepository;
    private final VenueRepository venueRepository;
    private final AnniversaryRepository anniversaryRepository;
    private final BirthdayPartyRepository birthdayPartyRepository;
    private final GetTogetherRepository getTogetherRepository;
    private final WeddingRepository weddingRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Map<String, Object> getEventEntityNestedObjects (EventEntity event) {
        final Long userId = event.getUserId();
        final UserEntity user = userId == null ?
                null :
                this.userRepository.findById(userId).orElse(null);

        if (userId != null && user == null) return Map.of();

        final Long venueId = event.getVenueId();
        final VenueEntity venue = venueId == null ?
                null :
                this.venueRepository.findById(venueId).orElse(null);

        if (venueId != null && venue == null) return Map.of();

        final Map<String, Object> nestedObjects = new HashMap<>();

        nestedObjects.put("user", user);
        nestedObjects.put("venue", venue);

        return nestedObjects;
    }

    private void putNestedObjectsIntoEventFullEntity (Map<String, Object> nestedObjects, EventFullEntity eventFullEntity) {
        eventFullEntity.setUser((UserEntity) nestedObjects.get("user"));
        eventFullEntity.setVenue((VenueEntity) nestedObjects.get("venue"));
    }

    private <T> boolean insertEventSubCategoryHelper (T entity, SuperEventRepository<T> repository, Consumer<T> setter, boolean isUpdate) {
        if (entity == null) return true;

        final T addedEntity = isUpdate ? repository.update(entity) : repository.add(entity);

        if (addedEntity == null) return false;

        setter.accept(addedEntity);
        return true;
    }

    private boolean insertEventSubCategory (EventEntity event, EventFullEntity eventFullEntity) {
        return switch (event.getEventType()) {
            case ANNIVERSARIES -> this.insertEventSubCategoryHelper(event.getAnniversary(), this.anniversaryRepository, eventFullEntity::setAnniversary, false);
            case BIRTHDAY_PARTIES -> this.insertEventSubCategoryHelper(event.getBirthdayParty(), this.birthdayPartyRepository, eventFullEntity::setBirthdayParty, false);
            case GET_TOGETHER -> this.insertEventSubCategoryHelper(event.getGetTogether(), this.getTogetherRepository, eventFullEntity::setGetTogether, false);
            case WEDDING -> this.insertEventSubCategoryHelper(event.getWedding(), this.weddingRepository, eventFullEntity::setWedding, false);
        };
    }

    private boolean updateEventSubCategory (EventEntity event, EventFullEntity eventFullEntity) {
        return switch (event.getEventType()) {
            case ANNIVERSARIES -> this.insertEventSubCategoryHelper(event.getAnniversary(), this.anniversaryRepository, eventFullEntity::setAnniversary, true);
            case BIRTHDAY_PARTIES -> this.insertEventSubCategoryHelper(event.getBirthdayParty(), this.birthdayPartyRepository, eventFullEntity::setBirthdayParty, true);
            case GET_TOGETHER -> this.insertEventSubCategoryHelper(event.getGetTogether(), this.getTogetherRepository, eventFullEntity::setGetTogether, true);
            case WEDDING -> this.insertEventSubCategoryHelper(event.getWedding(), this.weddingRepository, eventFullEntity::setWedding, true);
        };
    }

    @Override
    @Transactional
    public EventFullEntity add (EventEntity event) {
        try {
            final Query eventInsertQuery = this.entityManager.createNativeQuery("""
				INSERT INTO event
				(user_id, venue_id, location, event_date, start_time, end_time, event_type, head_count, total_price, budget_type, event_status)
				VALUES (:user_id, :venue_id, :location, :event_date, :start_time, :end_time, :event_type, :head_count, :total_price, :budget_type, :event_status)
				""", EventEntity.class);

            eventInsertQuery.setParameter("user_id", event.getUserId());
            eventInsertQuery.setParameter("venue_id", event.getVenueId());
            eventInsertQuery.setParameter("location", event.getLocation());
            eventInsertQuery.setParameter("event_date", event.getEventDate());
            eventInsertQuery.setParameter("start_time", event.getStartTime());
            eventInsertQuery.setParameter("end_time", event.getEndTime());
            eventInsertQuery.setParameter("event_type", event.getEventType().name());
            eventInsertQuery.setParameter("head_count", event.getHeadCount());
            eventInsertQuery.setParameter("total_price", event.getTotalPrice());
            eventInsertQuery.setParameter("budget_type", event.getBudgetType().name());
            eventInsertQuery.setParameter("event_status", EventStatusType.ON_HOLD.name());

            eventInsertQuery.executeUpdate();

            final Long insertedId = ((Number) this.entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).longValue();
            final EventEntity addedEventEntity = this.entityManager.find(EventEntity.class, insertedId);

            final Map<String, Object> nestedObjects = this.getEventEntityNestedObjects(event);

            if (nestedObjects.isEmpty()) throw new IllegalStateException("Nested objects missing. Cannot continue.");

            final EventFullEntity eventFullEntity = EventFullEntity.builder()
                    .id(insertedId)
                    .eventDate(addedEventEntity.getEventDate())
                    .startTime(addedEventEntity.getStartTime())
                    .endTime(addedEventEntity.getEndTime())
                    .eventType(addedEventEntity.getEventType())
                    .headCount(addedEventEntity.getHeadCount())
                    .totalPrice(addedEventEntity.getTotalPrice())
                    .budgetType(addedEventEntity.getBudgetType())
                    .eventStatus(addedEventEntity.getEventStatus())
                    .location(addedEventEntity.getLocation())
                    .build();

            if (!this.insertEventSubCategory(event, eventFullEntity)) throw new IllegalStateException("Failed to insert subcategories.");

            this.putNestedObjectsIntoEventFullEntity(nestedObjects, eventFullEntity);
            return eventFullEntity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            throw exception;
        }
    }

    @Override
    @Transactional
    public EventFullEntity update (EventEntity event) {
        try {
            final Query eventUpdateQuery = this.entityManager.createNativeQuery("""
                UPDATE event
                SET user_id = :user_id, venue_id = :venue_id, location = :location, event_date = :event_date, start_time = :start_time, end_time = :end_time, event_type = :event_type, head_count = :head_count, total_price = :total_price, budget_type = :budget_type, event_status = :event_status
                WHERE id = :id AND is_deleted = FALSE
                """);

            eventUpdateQuery.setParameter("user_id", event.getUserId());
            eventUpdateQuery.setParameter("venue_id", event.getVenueId());
            eventUpdateQuery.setParameter("location", event.getLocation());
            eventUpdateQuery.setParameter("event_date", event.getEventDate());
            eventUpdateQuery.setParameter("start_time", event.getStartTime());
            eventUpdateQuery.setParameter("end_time", event.getEndTime());
            eventUpdateQuery.setParameter("event_type", event.getEventType().name());
            eventUpdateQuery.setParameter("head_count", event.getHeadCount());
            eventUpdateQuery.setParameter("total_price", event.getTotalPrice());
            eventUpdateQuery.setParameter("budget_type", event.getBudgetType().name());
            eventUpdateQuery.setParameter("event_status", event.getEventStatus());

            eventUpdateQuery.executeUpdate();


            final Map<String, Object> nestedObjects = this.getEventEntityNestedObjects(event);

            if (nestedObjects.isEmpty()) throw new IllegalStateException("Nested objects missing. Cannot continue.");

            final EventFullEntity eventFullEntity = EventFullEntity.builder()
                    .id(event.getId())
                    .eventDate(event.getEventDate())
                    .startTime(event.getStartTime())
                    .endTime(event.getEndTime())
                    .eventType(event.getEventType())
                    .headCount(event.getHeadCount())
                    .budgetType(event.getBudgetType())
                    .eventStatus(event.getEventStatus())
                    .location(event.getLocation())
                    .build();

            if (!this.updateEventSubCategory(event, eventFullEntity)) throw new IllegalStateException("Failed to insert subcategories.");

            this.putNestedObjectsIntoEventFullEntity(nestedObjects, eventFullEntity);

            return eventFullEntity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return null;
        }
    }

    @Override
    public EventFullEntity get (Long id) {
        try {
            final Query eventGetQuery = this.entityManager.createNativeQuery("""
				SELECT user_id, venue_id, location, event_date, start_time, end_time, event_type, head_count, total_price, budget_type, event_status FROM event
				WHERE id = :id AND is_deleted = FALSE
				""");

            eventGetQuery.setParameter("id", id);

            final Object[] results = (Object[]) eventGetQuery.getSingleResult();

            final EventFullEntity eventFullEntity = EventFullEntity.builder()
                .id(id)
                .location((String) results[2])
                .eventDate(((Date) results[3]).toLocalDate())
                .startTime(((Time) results[4]).toLocalTime())
                .endTime(((Time) results[5]).toLocalTime())
                .eventType(EventType.fromName((String) results[6]))
                .headCount((Integer) results[7])
                .totalPrice((Double) results[8])
                .budgetType(BudgetType.fromName((String) results[9]))
                .eventStatus(EventStatusType.fromName((String) results[10]))
                .build();

            eventFullEntity.setUser(this.userRepository.findById((Long) results[0]).orElse(null));

            if (results[1] != null) eventFullEntity.setVenue(this.venueRepository.findById(((Number) results[1]).longValue()).orElse(null));

            switch (eventFullEntity.getEventType()) {
                case ANNIVERSARIES -> eventFullEntity.setAnniversary(this.anniversaryRepository.getByEventId(id));
                case BIRTHDAY_PARTIES -> eventFullEntity.setBirthdayParty(this.birthdayPartyRepository.getByEventId(id));
                case GET_TOGETHER -> eventFullEntity.setGetTogether(this.getTogetherRepository.getByEventId(id));
                case WEDDING -> eventFullEntity.setWedding(this.weddingRepository.getByEventId(id));
            }

            return eventFullEntity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return null;
        }
    }

    private List<EventFullEntity> getListOfEventFullEntitiesByMatchQuery (String condition, Object ...binds) {
        final List<EventFullEntity> eventFullEntities = new ArrayList<>();

        try {
            final Query getAllEventsQuery = this.entityManager.createNativeQuery(String.format("""
                SELECT id, user_id, venue_id, location, event_date, start_time, end_time, event_type, head_count, total_price, budget_type, event_status
                FROM event
                WHERE is_deleted = FALSE%s
                """, condition));

            for (int a = 0; a < binds.length; a++)
                getAllEventsQuery.setParameter(a + 1, binds[a]);

            final List<Object[]> resultsList = getAllEventsQuery.getResultList();

            resultsList.forEach(results -> {
                final Long id = ((Number) results[0]).longValue();
                final EventFullEntity entity = EventFullEntity.builder()
                    .id(id)
                    .location((String) results[3])
                    .eventDate(((Date) results[4]).toLocalDate())
                    .startTime(((Time) results[5]).toLocalTime())
                    .endTime(((Time) results[6]).toLocalTime())
                    .eventType(EventType.fromName((String) results[7]))
                    .headCount(((Number) results[8]).intValue())
                    .totalPrice(((Number) results[9]).doubleValue())
                    .budgetType(BudgetType.fromName((String) results[10]))
                    .eventStatus(EventStatusType.fromName((String) results[11]))
                    .build();

                entity.setUser(this.userRepository.findById(((Number) results[1]).longValue()).orElse(null));

                switch (entity.getEventType()) {
                    case ANNIVERSARIES -> entity.setAnniversary(this.anniversaryRepository.getByEventSummaryId(id));
                    case BIRTHDAY_PARTIES -> entity.setBirthdayParty(this.birthdayPartyRepository.getByEventSummaryId(id));
                    case GET_TOGETHER -> entity.setGetTogether(this.getTogetherRepository.getByEventSummaryId(id));
                    case WEDDING -> entity.setWedding(this.weddingRepository.getByEventSummaryId(id));
                }

                if (results[2] != null) entity.setVenue(this.venueRepository.findById(((Number) results[2]).longValue()).orElse(null));

                eventFullEntities.add(entity);
            });
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
        }

        return eventFullEntities;
    }

    @Override
    public List<EventFullEntity> getAll () {
        return this.getListOfEventFullEntitiesByMatchQuery("");
    }

    @Override
    public List<EventFullEntity> getAllByDate (LocalDate date) {
        return this.getListOfEventFullEntitiesByMatchQuery(" WHERE event_date = ?", date);
    }

    @Override
    public List<EventFullEntity> getAllByLocation (Long locationId) {
        return this.getListOfEventFullEntitiesByMatchQuery(" WHERE location_id = ?", locationId);
    }

    @Override
    public List<EventFullEntity> getAllByUser (Long userId) {
        return this.getListOfEventFullEntitiesByMatchQuery(" WHERE user_id = ?", userId);
    }

    @Override
    @Transactional
    public boolean delete (Long id) {
        try {
            final Query deleteQuery = this.entityManager.createNativeQuery("""
                UPDATE event SET is_deleted = TRUE WHERE id = :id AND is_deleted = FALSE
            """);
            deleteQuery.setParameter("id", id);

            return deleteQuery.executeUpdate() > 0;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return false;
        }
    }
}