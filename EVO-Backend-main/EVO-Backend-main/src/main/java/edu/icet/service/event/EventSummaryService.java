package edu.icet.service.event;

import edu.icet.dto.event.EventSummary;
import edu.icet.dto.event.EventSummaryFull;
import edu.icet.dto.supplier.Supplier;

import java.time.LocalDate;
import java.util.List;

public interface EventSummaryService {
    EventSummaryFull add (EventSummary eventSummary);
    EventSummaryFull update (EventSummary eventSummary);
    EventSummaryFull get (Long id);
    List<EventSummaryFull> getAll ();
    List<EventSummaryFull> getAllByDate (LocalDate date);
    List<EventSummaryFull> getAllByLocation (Long locationId);
    List<EventSummaryFull> getAllByUser (Long userId);
    boolean delete (Long id);
    boolean confirm (Long id);
    boolean addSuppliersToSummary(Long eventSummaryID, List<Supplier> suppliers);
    List<Supplier> getSuppliersOfSummary(Long eventSummaryID);
}