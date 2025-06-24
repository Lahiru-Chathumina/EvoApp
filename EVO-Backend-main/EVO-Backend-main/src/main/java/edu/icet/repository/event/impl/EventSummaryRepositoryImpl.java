package edu.icet.repository.event.impl;

import edu.icet.entity.customer.UserEntity;
import edu.icet.entity.event.EventEntity;
import edu.icet.entity.event.EventSummaryEntity;
import edu.icet.entity.event.EventSummaryFullEntity;
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
public class EventSummaryRepositoryImpl implements EventSummaryRepository {
    private final Logger logger;
    private final VenueRepository venueRepository;
    private final UserRepository userRepository;
    private final AnniversaryRepository anniversaryRepository;
    private final BirthdayPartyRepository birthdayPartyRepository;
    private final GetTogetherRepository getTogetherRepository;
    private final WeddingRepository weddingRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Map<String, Object> getEventEntityNestedObjects (EventSummaryEntity eventSummary) {
        final Long userId = eventSummary.getUserId();
        final UserEntity user = userId == null ?
                null :
                this.userRepository.findById(userId).orElse(null);

        if (userId != null && user == null) return Map.of();

        final Long venueId = eventSummary.getVenueId();
        final VenueEntity venue = venueId == null ?
                null :
                this.venueRepository.findById(venueId).orElse(null);

        if (venueId != null && venue == null) return Map.of();

        final Map<String, Object> nestedObjects = new HashMap<>();

        nestedObjects.put("user", user);
        nestedObjects.put("venue", venue);

        return nestedObjects;
    }

    private void putNestedObjectsIntoEventFullEntity (Map<String, Object> nestedObjects, EventSummaryFullEntity eventSummaryFullEntity) {
        eventSummaryFullEntity.setUser((UserEntity) nestedObjects.get("user"));
        eventSummaryFullEntity.setVenue((VenueEntity) nestedObjects.get("venue"));
    }

    private <T> boolean insertEventSubCategoryHelper (T entity, SuperEventRepository<T> repository, Consumer<T> setter, boolean isUpdate) {
        if (entity == null) return true;

        final T addedEntity = isUpdate ? repository.update(entity) : repository.add(entity);

        if (addedEntity == null) return false;

        setter.accept(addedEntity);
        return true;
    }

    private boolean insertEventSubCategory (EventSummaryEntity eventSummary, EventSummaryFullEntity eventSummaryFullEntity) {
        final EventType eventType = eventSummary.getEventType();

        return switch (eventType) {
            case ANNIVERSARIES -> {
                eventSummary.getAnniversary().setEventSummaryId(eventSummaryFullEntity.getId());
                yield this.insertEventSubCategoryHelper(eventSummary.getAnniversary(), this.anniversaryRepository, eventSummaryFullEntity::setAnniversary, false);
            }
            case BIRTHDAY_PARTIES -> {
                eventSummary.getBirthdayParty().setEventSummaryId(eventSummaryFullEntity.getId());
                yield this.insertEventSubCategoryHelper(eventSummary.getBirthdayParty(), this.birthdayPartyRepository, eventSummaryFullEntity::setBirthdayParty, false);
            }
            case GET_TOGETHER -> {
                eventSummary.getGetTogether().setEventSummaryId(eventSummaryFullEntity.getId());
                yield this.insertEventSubCategoryHelper(eventSummary.getGetTogether(), this.getTogetherRepository, eventSummaryFullEntity::setGetTogether, false);
            }
            case WEDDING -> {
                eventSummary.getWedding().setEventSummaryId(eventSummaryFullEntity.getId());
                yield this.insertEventSubCategoryHelper(eventSummary.getWedding(), this.weddingRepository, eventSummaryFullEntity::setWedding, false);
            }
        };
    }

    private boolean updateEventSubCategory (EventSummaryEntity eventSummary, EventSummaryFullEntity eventSummaryFullEntity) {
        final EventType eventType = eventSummary.getEventType();

        return switch (eventType) {
            case ANNIVERSARIES -> {
                eventSummary.getAnniversary().setEventSummaryId(eventSummaryFullEntity.getId());
                yield this.insertEventSubCategoryHelper(eventSummary.getAnniversary(), this.anniversaryRepository, eventSummaryFullEntity::setAnniversary, true);
            }
            case BIRTHDAY_PARTIES -> {
                eventSummary.getBirthdayParty().setEventSummaryId(eventSummaryFullEntity.getId());
                yield this.insertEventSubCategoryHelper(eventSummary.getBirthdayParty(), this.birthdayPartyRepository, eventSummaryFullEntity::setBirthdayParty, true);
            }
            case GET_TOGETHER -> {
                eventSummary.getGetTogether().setEventSummaryId(eventSummaryFullEntity.getId());
                yield this.insertEventSubCategoryHelper(eventSummary.getGetTogether(), this.getTogetherRepository, eventSummaryFullEntity::setGetTogether, true);
            }
            case WEDDING -> {
                eventSummary.getWedding().setEventSummaryId(eventSummaryFullEntity.getId());
                yield this.insertEventSubCategoryHelper(eventSummary.getWedding(), this.weddingRepository, eventSummaryFullEntity::setWedding, true);
            }
        };
    }

    @Override
    @Transactional
    public EventSummaryFullEntity add (EventSummaryEntity eventSummary) {
        try {
            final Query eventSummaryInsertQuery = this.entityManager.createNativeQuery("""
				INSERT INTO event_summary
				(user_id, venue_id, location, event_date, start_time, end_time, event_type, head_count, total_price, budget_type)
				VALUES (:user_id, :venue_id, :location, :event_date, :start_time, :end_time, :event_type, :head_count, :total_price, :budget_type)
				""");

            eventSummaryInsertQuery.setParameter("user_id", eventSummary.getUserId());
            eventSummaryInsertQuery.setParameter("venue_id", eventSummary.getVenueId());
            eventSummaryInsertQuery.setParameter("location", eventSummary.getLocation());
            eventSummaryInsertQuery.setParameter("event_date", eventSummary.getEventDate());
            eventSummaryInsertQuery.setParameter("start_time", eventSummary.getStartTime());
            eventSummaryInsertQuery.setParameter("end_time", eventSummary.getEndTime());
            eventSummaryInsertQuery.setParameter("event_type", eventSummary.getEventType().name());
            eventSummaryInsertQuery.setParameter("head_count", eventSummary.getHeadCount());
            eventSummaryInsertQuery.setParameter("total_price", eventSummary.getTotalPrice());
            eventSummaryInsertQuery.setParameter("budget_type", eventSummary.getBudgetType().name());

            eventSummaryInsertQuery.executeUpdate();

            final Long insertedId = ((Number) this.entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).longValue();
            final EventSummaryEntity addedEventSummary = this.entityManager.find(EventSummaryEntity.class, insertedId);

            final Map<String, Object> nestedObjects = this.getEventEntityNestedObjects(eventSummary);

            if (nestedObjects.isEmpty()) throw new IllegalStateException("Nested objects missing. Cannot continue.");

            final EventSummaryFullEntity eventSummaryFullEntity = EventSummaryFullEntity.builder()
                    .id(insertedId)
                    .eventDate(addedEventSummary.getEventDate())
                    .startTime(addedEventSummary.getStartTime())
                    .endTime(addedEventSummary.getEndTime())
                    .eventType(addedEventSummary.getEventType())
                    .headCount(addedEventSummary.getHeadCount())
                    .totalPrice(addedEventSummary.getTotalPrice())
                    .budgetType(addedEventSummary.getBudgetType())
                    .location(addedEventSummary.getLocation())
                    .build();

            if (!this.insertEventSubCategory(eventSummary, eventSummaryFullEntity)) throw new IllegalStateException("Failed to insert subcategories.");

            this.putNestedObjectsIntoEventFullEntity(nestedObjects, eventSummaryFullEntity);

            return eventSummaryFullEntity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            throw exception;
        }
    }

    @Override
    @Transactional
    public EventSummaryFullEntity update (EventSummaryEntity eventSummary) {
        try {
            final Query eventSummaryInsertQuery = this.entityManager.createNativeQuery("""
					UPDATE event_summary
					SET user_id = :user_id, venue_id = :venue_id, location = :location, event_date = :event_date, start_time = :start_time, end_time = :end_time, head_count = :head_count, total_price = :total_price, budget_type = :budget_type
					WHERE is_deleted = FALSE AND id = :id
					""");

            eventSummaryInsertQuery.setParameter("user_id", eventSummary.getUserId());
            eventSummaryInsertQuery.setParameter("venue_id", eventSummary.getVenueId());
            eventSummaryInsertQuery.setParameter("location", eventSummary.getLocation());
            eventSummaryInsertQuery.setParameter("event_date", eventSummary.getEventDate());
            eventSummaryInsertQuery.setParameter("start_time", eventSummary.getStartTime());
            eventSummaryInsertQuery.setParameter("end_time", eventSummary.getEndTime());
            eventSummaryInsertQuery.setParameter("head_count", eventSummary.getHeadCount());
            eventSummaryInsertQuery.setParameter("total_price", eventSummary.getTotalPrice());
            eventSummaryInsertQuery.setParameter("budget_type", eventSummary.getBudgetType().name());
            eventSummaryInsertQuery.setParameter("id", eventSummary.getId());

            if (eventSummaryInsertQuery.executeUpdate() == 0) throw new IllegalStateException("No rows updated. Maybe wrong ID or it's soft-deleted.");

            final EventSummaryEntity updatedEventSummary = this.entityManager.find(EventSummaryEntity.class, eventSummary.getId());

            if (updatedEventSummary == null) throw new IllegalStateException("Couldn't find updated event summary.");

            final Map<String, Object> nestedObjects = this.getEventEntityNestedObjects(eventSummary);

            if (nestedObjects.isEmpty()) throw new IllegalStateException("Nested objects missing. Cannot continue.");

            final EventSummaryFullEntity eventSummaryFullEntity = EventSummaryFullEntity.builder()
                    .id(eventSummary.getId())
                    .eventDate(updatedEventSummary.getEventDate())
                    .startTime(updatedEventSummary.getStartTime())
                    .endTime(updatedEventSummary.getEndTime())
                    .eventType(updatedEventSummary.getEventType())
                    .headCount(updatedEventSummary.getHeadCount())
                    .totalPrice(updatedEventSummary.getTotalPrice())
                    .budgetType(updatedEventSummary.getBudgetType())
                    .location(updatedEventSummary.getLocation())
                    .build();

            if (!this.updateEventSubCategory(eventSummary, eventSummaryFullEntity)) throw new IllegalStateException("Failed to update subcategories.");

            this.putNestedObjectsIntoEventFullEntity(nestedObjects, eventSummaryFullEntity);

            return eventSummaryFullEntity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            throw exception;
        }
    }

    @Override
    public EventSummaryFullEntity get (Long id) {
        try {
            final Query eventSummaryGetQuery = this.entityManager.createNativeQuery("""
				SELECT user_id, venue_id, location, event_date, start_time, end_time, event_type, head_count, total_price, budget_type FROM event_summary
				WHERE id = :id AND is_deleted = FALSE
				""");

            eventSummaryGetQuery.setParameter("id", id);

            final Object[] results = (Object[]) eventSummaryGetQuery.getSingleResult();

            final EventSummaryFullEntity eventSummaryFullEntity = EventSummaryFullEntity.builder()
                    .id(id)
                    .location((String) results[2])
                    .eventDate(((Date) results[3]).toLocalDate())
                    .startTime(((Time) results[4]).toLocalTime())
                    .endTime(((Time) results[5]).toLocalTime())
                    .eventType(EventType.fromName((String) results[6]))
                    .headCount((Integer) results[7])
                    .totalPrice((Double) results[8])
                    .budgetType(BudgetType.fromName((String) results[9]))
                    .build();

            eventSummaryFullEntity.setUser(this.userRepository.findById((Long) results[0]).orElse(null));

            if (results[1] != null) eventSummaryFullEntity.setVenue(this.venueRepository.findById(((Number) results[1]).longValue()).orElse(null));

            switch (eventSummaryFullEntity.getEventType()) {
                case ANNIVERSARIES -> eventSummaryFullEntity.setAnniversary(this.anniversaryRepository.getByEventSummaryId(id));
                case BIRTHDAY_PARTIES -> eventSummaryFullEntity.setBirthdayParty(this.birthdayPartyRepository.getByEventSummaryId(id));
                case GET_TOGETHER -> eventSummaryFullEntity.setGetTogether(this.getTogetherRepository.getByEventSummaryId(id));
                case WEDDING -> eventSummaryFullEntity.setWedding(this.weddingRepository.getByEventSummaryId(id));
            }

            return eventSummaryFullEntity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return null;
        }
    }

    private List<EventSummaryFullEntity> getListOfEventSummaryFullEntitiesByMatchQuery (String condition, Object ...binds) {
        final List<EventSummaryFullEntity> eventSummaryFullEntities = new ArrayList<>();

        try {
            final Query getALlEventSummariesQuery = this.entityManager.createNativeQuery(String.format("SELECT id, user_id, venue_id, location, event_date, start_time, end_time, event_type, head_count, total_price, budget_type FROM event_summary WHERE is_deleted = FALSE%s", condition));

            for (int a = 0; a < binds.length; a++)
                getALlEventSummariesQuery.setParameter(a + 1, binds[a]);

            final List<Object[]> resultsList = getALlEventSummariesQuery.getResultList();

            resultsList.forEach(results -> {
                final Long id = ((Number) results[0]).longValue();
                final EventSummaryFullEntity entity = EventSummaryFullEntity.builder()
                        .id(id)
                        .location((String) results[3])
                        .eventDate(((Date) results[4]).toLocalDate())
                        .startTime(((Time) results[5]).toLocalTime())
                        .endTime(((Time) results[6]).toLocalTime())
                        .eventType(EventType.fromName((String) results[7]))
                        .headCount(((Number) results[8]).intValue())
                        .totalPrice(((Number) results[9]).doubleValue())
                        .budgetType(BudgetType.fromName((String) results[10]))
                        .build();

                entity.setUser(this.userRepository.findById(((Number) results[1]).longValue()).orElse(null));

                switch (entity.getEventType()) {
                    case ANNIVERSARIES -> entity.setAnniversary(this.anniversaryRepository.getByEventSummaryId(id));
                    case BIRTHDAY_PARTIES -> entity.setBirthdayParty(this.birthdayPartyRepository.getByEventSummaryId(id));
                    case GET_TOGETHER -> entity.setGetTogether(this.getTogetherRepository.getByEventSummaryId(id));
                    case WEDDING -> entity.setWedding(this.weddingRepository.getByEventSummaryId(id));
                }

                if (results[2] != null) entity.setVenue(this.venueRepository.findById(((Number) results[2]).longValue()).orElse(null));

                eventSummaryFullEntities.add(entity);
            });
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
        }

        return eventSummaryFullEntities;
    }

    @Override
    public List<EventSummaryFullEntity> getAll () {
        return this.getListOfEventSummaryFullEntitiesByMatchQuery("");
    }

    @Override
    public List<EventSummaryFullEntity> getAllByDate (LocalDate date) {
        return this.getListOfEventSummaryFullEntitiesByMatchQuery(" AND event_date = ?", date);
    }

    @Override
    public List<EventSummaryFullEntity> getAllByLocation (Long locationId) {
        return this.getListOfEventSummaryFullEntitiesByMatchQuery(" AND location_id = ?", locationId);
    }

    @Override
    public List<EventSummaryFullEntity> getAllByUser (Long userId) {
        return this.getListOfEventSummaryFullEntitiesByMatchQuery(" AND user_id = ?", userId);
    }

    @Override
    @Transactional
    public boolean delete (Long id) {
        try {
            final Query deleteEventSummaryQuery = this.entityManager.createNativeQuery("""
				UPDATE event_summary
				SET is_deleted = TRUE
				WHERE is_deleted = FALSE AND id = :id
				""");

            deleteEventSummaryQuery.setParameter("id", id);

            return deleteEventSummaryQuery.executeUpdate() > 0;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return false;
        }
    }

    private EventSummaryEntity getEventSummaryForConfirm (Long eventSummaryId) {
        try {
            final Query eventSummaryGetQuery = this.entityManager.createNativeQuery("""
				SELECT user_id, venue_id, location, event_date, start_time, end_time, event_type, head_count, total_price, budget_type, location FROM event_summary
				WHERE id = :id AND is_deleted = FALSE
				""");

            eventSummaryGetQuery.setParameter("id", eventSummaryId);

            final Object[] results = (Object[]) eventSummaryGetQuery.getSingleResult();

            final EventSummaryEntity eventSummaryEntity = EventSummaryEntity.builder()
                .id(eventSummaryId)
                .userId((Long) results[0])
                .venueId((Long) results[1])
                .location((String) results[2])
                .eventDate(((Date) results[3]).toLocalDate())
                .startTime(((Time) results[4]).toLocalTime())
                .endTime(((Time) results[5]).toLocalTime())
                .eventType(EventType.fromName((String) results[6]))
                .headCount((Integer) results[7])
                .totalPrice((Double) results[8])
                .budgetType(BudgetType.fromName((String) results[9]))
                .build();

            switch (eventSummaryEntity.getEventType()) {
                case ANNIVERSARIES -> eventSummaryEntity.setAnniversary(this.anniversaryRepository.getByEventSummaryId(eventSummaryId));
                case BIRTHDAY_PARTIES -> eventSummaryEntity.setBirthdayParty(this.birthdayPartyRepository.getByEventSummaryId(eventSummaryId));
                case GET_TOGETHER -> eventSummaryEntity.setGetTogether(this.getTogetherRepository.getByEventSummaryId(eventSummaryId));
                case WEDDING -> eventSummaryEntity.setWedding(this.weddingRepository.getByEventSummaryId(eventSummaryId));
            }

            return eventSummaryEntity;
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return null;
        }
    }

    private boolean insertEventForEventConfirm (EventEntity event, Long eventSummaryId) {
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

        switch (event.getEventType()) {
            case ANNIVERSARIES -> this.anniversaryRepository.setEventId(eventSummaryId, insertedId);
            case BIRTHDAY_PARTIES -> this.birthdayPartyRepository.setEventId(eventSummaryId, insertedId);
            case GET_TOGETHER -> this.getTogetherRepository.setEventId(eventSummaryId, insertedId);
            case WEDDING -> this.weddingRepository.setEventId(eventSummaryId, insertedId);
        }

        return true;
    }

    @Override
    @Transactional
    public boolean confirm (Long id) {
        try {
            final EventSummaryEntity eventSummaryEntity = this.getEventSummaryForConfirm(id);

            if (eventSummaryEntity == null) return false;

            final EventEntity event = EventEntity.builder()
                    .id(id)
                    .userId(eventSummaryEntity.getUserId())
                    .venueId(eventSummaryEntity.getVenueId())
                    .location(eventSummaryEntity.getLocation())
                    .eventDate(eventSummaryEntity.getEventDate())
                    .startTime(eventSummaryEntity.getStartTime())
                    .endTime(eventSummaryEntity.getEndTime())
                    .eventType(eventSummaryEntity.getEventType())
                    .headCount(eventSummaryEntity.getHeadCount())
                    .totalPrice(eventSummaryEntity.getTotalPrice())
                    .budgetType(eventSummaryEntity.getBudgetType())
                    .anniversary(eventSummaryEntity.getAnniversary())
                    .birthdayParty(eventSummaryEntity.getBirthdayParty())
                    .getTogether(eventSummaryEntity.getGetTogether())
                    .wedding(eventSummaryEntity.getWedding())
                    .build();

            return this.delete(id) && this.insertEventForEventConfirm(event, id);
        } catch (Exception exception) {
            this.logger.error(exception.getMessage());
            return false;
        }
    }
}