package edu.icet.service.event.impl;

import edu.icet.dto.event.Event;
import edu.icet.dto.event.EventFull;
import edu.icet.entity.event.EventEntity;
import edu.icet.entity.event.EventFullEntity;
import edu.icet.repository.event.EventRepository;
import edu.icet.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper mapper;

    @Override
    public EventFull add (Event event) {
        final EventFullEntity addedEvent = this.eventRepository.add(this.mapper.map(event, EventEntity.class));
        return addedEvent == null ? null : this.mapper.map(addedEvent, EventFull.class);
    }

    @Override
    public EventFull update (Event event) {
        final EventFullEntity updatedEvent = this.eventRepository.update(this.mapper.map(event, EventEntity.class));
        return updatedEvent == null ? null : this.mapper.map(updatedEvent, EventFull.class);
    }

    @Override
    public EventFull get (Long id) {
        final EventFullEntity receivedEvent = this.eventRepository.get(id);
        return receivedEvent == null ? null : this.mapper.map(receivedEvent, EventFull.class);
    }

    @Override
    public List<EventFull> getAll () {
        return this.eventRepository.getAll().stream().map(eventFullEntity -> this.mapper.map(eventFullEntity, EventFull.class)).toList();
    }

    @Override
    public List<EventFull> getAllByDate (LocalDate date) {
        return this.eventRepository.getAllByDate(date).stream().map(eventFullEntity -> this.mapper.map(eventFullEntity, EventFull.class)).toList();
    }

    @Override
    public List<EventFull> getAllByLocation (Long locationId) {
        return this.eventRepository.getAllByLocation(locationId).stream().map(eventFullEntity -> this.mapper.map(eventFullEntity, EventFull.class)).toList();
    }

    @Override
    public List<EventFull> getAllByUser (Long userId) {
        return this.eventRepository.getAllByUser(userId).stream().map(eventFullEntity -> this.mapper.map(eventFullEntity, EventFull.class)).toList();
    }

    @Override
    public boolean delete (Long id) {
        return this.eventRepository.delete(id);
    }
}