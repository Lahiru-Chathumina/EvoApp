package edu.icet.service.event.impl;

import edu.icet.dto.event.EventSummary;
import edu.icet.dto.event.EventSummaryFull;
import edu.icet.dto.supplier.Supplier;
import edu.icet.entity.event.EventSummaryEntity;
import edu.icet.entity.event.EventSummaryFullEntity;
import edu.icet.entity.event.EventSummarySuppliersEntity;
import edu.icet.entity.supplier.SupplierEntity;
import edu.icet.repository.event.EventSummaryRepository;
import edu.icet.repository.event.EventSummarySuppliersRepository;
import edu.icet.service.event.EventSummaryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class EventSummaryServiceImpl implements EventSummaryService {
    private final EventSummaryRepository eventSummaryRepository;
    private final EventSummarySuppliersRepository eventSummarySuppliersRepository;
    private final ModelMapper mapper;

    @Override
    public EventSummaryFull add (EventSummary eventSummary) {
        final EventSummaryFullEntity addedEventSummary = this.eventSummaryRepository.add(this.mapper.map(eventSummary, EventSummaryEntity.class));
        return addedEventSummary == null ? null : this.mapper.map(addedEventSummary, EventSummaryFull.class);
    }

    @Override
    public EventSummaryFull update (EventSummary eventSummary) {
        final EventSummaryFullEntity updatedEventSummary = this.eventSummaryRepository.update(this.mapper.map(eventSummary, EventSummaryEntity.class));
        return updatedEventSummary == null ? null : this.mapper.map(updatedEventSummary, EventSummaryFull.class);
    }

    @Override
    public EventSummaryFull get (Long id) {
        final EventSummaryFullEntity receivedEventSummary = this.eventSummaryRepository.get(id);
        return receivedEventSummary == null ? null : this.mapper.map(receivedEventSummary, EventSummaryFull.class);
    }

    @Override
    public List<EventSummaryFull> getAll () {
        return this.eventSummaryRepository.getAll().stream().map(eventSummaryFullEntity -> this.mapper.map(eventSummaryFullEntity, EventSummaryFull.class)).toList();
    }

    @Override
    public List<EventSummaryFull> getAllByDate (LocalDate date) {
        return this.eventSummaryRepository.getAllByDate(date).stream().map(eventSummaryFullEntity -> this.mapper.map(eventSummaryFullEntity, EventSummaryFull.class)).toList();
    }

    @Override
    public List<EventSummaryFull> getAllByLocation (Long locationId) {
        return this.eventSummaryRepository.getAllByLocation(locationId).stream().map(eventSummaryFullEntity -> this.mapper.map(eventSummaryFullEntity, EventSummaryFull.class)).toList();
    }

    @Override
    public List<EventSummaryFull> getAllByUser (Long userId) {
        return this.eventSummaryRepository.getAllByUser(userId).stream().map(eventSummaryFullEntity -> this.mapper.map(eventSummaryFullEntity, EventSummaryFull.class)).toList();
    }

    @Override
    public boolean delete (Long id) {
        return this.eventSummaryRepository.delete(id);
    }

    @Override
    public boolean confirm (Long id) {
        return this.eventSummaryRepository.confirm(id);
    }

    @Override
    public boolean addSuppliersToSummary(Long eventSummaryID, List<Supplier> suppliers) {
        EventSummaryFullEntity eventSummaryFullEntity = eventSummaryRepository.get(eventSummaryID);

        if (eventSummaryFullEntity != null) {
                eventSummarySuppliersRepository.save(
                        EventSummarySuppliersEntity.builder()
                                .eventSummary(EventSummaryEntity.builder()
                                        .id(eventSummaryFullEntity.getId())
                                        .build())
                                .suppliers(suppliers.stream().map(supplier -> mapper.map(supplier, SupplierEntity.class)).toList())
                                .build()
                );

            return true;
        }

        return false;
    }

    @Override
    public List<Supplier> getSuppliersOfSummary(Long eventSummaryID) {
        EventSummaryFullEntity eventSummaryFullEntity = eventSummaryRepository.get(eventSummaryID);

        if (eventSummaryFullEntity != null) {
            return eventSummarySuppliersRepository.findByEventSummary(EventSummaryEntity.builder()
                    .id(eventSummaryID)
                    .build())
                    .getSuppliers()
                    .stream()
                    .map(supplierEntity -> mapper.map(supplierEntity, Supplier.class))
                    .toList();

        }

        return List.of();

    }
}