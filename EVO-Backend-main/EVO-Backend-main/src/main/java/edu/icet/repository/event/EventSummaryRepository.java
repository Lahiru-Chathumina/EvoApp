package edu.icet.repository.event;

import edu.icet.entity.event.EventSummaryEntity;
import edu.icet.entity.event.EventSummaryFullEntity;

import java.time.LocalDate;
import java.util.List;

public interface EventSummaryRepository {
  EventSummaryFullEntity add (EventSummaryEntity eventSummary);
  EventSummaryFullEntity update (EventSummaryEntity eventSummary);
  EventSummaryFullEntity get (Long id);
  List<EventSummaryFullEntity> getAll ();
  List<EventSummaryFullEntity> getAllByDate (LocalDate date);
  List<EventSummaryFullEntity> getAllByLocation (Long locationId);
  List<EventSummaryFullEntity> getAllByUser (Long userId);
  boolean delete (Long id);
  boolean confirm (Long id);
}