package edu.icet.repository.event;

import edu.icet.entity.event.EventEntity;
import edu.icet.entity.event.EventFullEntity;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository {
    EventFullEntity add (EventEntity event);
    EventFullEntity update (EventEntity event);
    EventFullEntity get (Long id);
    List<EventFullEntity> getAll ();
    List<EventFullEntity> getAllByDate (LocalDate date);
    List<EventFullEntity> getAllByLocation (Long locationId);
    List<EventFullEntity> getAllByUser (Long userId);
    boolean delete (Long id);
}