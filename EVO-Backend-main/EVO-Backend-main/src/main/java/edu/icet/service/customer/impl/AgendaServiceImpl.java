package edu.icet.service.customer.impl;

import edu.icet.dto.event.Agenda;
import edu.icet.dto.event.AgendaTask;
import edu.icet.entity.event.AgendaEntity;
import edu.icet.entity.event.AgendaTaskEntity;
import edu.icet.entity.event.EventEntity;
import edu.icet.entity.event.EventFullEntity;
import edu.icet.repository.event.AgendaRepository;
import edu.icet.repository.event.AgendaTaskRepository;
import edu.icet.repository.event.EventRepository;
import edu.icet.service.customer.AgendaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;
    private final AgendaTaskRepository agendaTaskRepository;
    private final EventRepository eventRepository;

    private final ModelMapper mapper;

    @Override
    @Transactional
    public boolean create(Agenda agenda) {
        AgendaEntity entity = new AgendaEntity();
        EventFullEntity event = eventRepository.get(agenda.getEventId().getId());

        if (event == null) return false;

        entity.setDate(agenda.getDate());
        entity.setTime(agenda.getTime());
        AgendaEntity savedEntity = agendaRepository.save(entity);
        return savedEntity.getId() != null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agenda> getAll() {
        return agendaRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean update(Agenda agenda) {
        AgendaEntity existing = agendaRepository.findById(agenda.getId()).orElse(null);
        if (existing == null) return false;

        EventFullEntity event = eventRepository.get(agenda.getEventId().getId());

        if (event == null) return false;

        existing.setDate(agenda.getDate());
        existing.setTime(agenda.getTime());
        agendaRepository.save(existing);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!agendaRepository.existsById(id)) return false;
        agendaRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Agenda getById(Long id) {
        return agendaRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean addTaskToAgenda(Long agendaId, AgendaTask newTask) {
        Optional<AgendaEntity> optionalAgenda = agendaRepository.findById(agendaId);
        if (optionalAgenda.isEmpty()) return false;

        AgendaEntity agenda = optionalAgenda.get();
        AgendaTaskEntity taskEntity = new AgendaTaskEntity();
        taskEntity.setTaskName(newTask.getTaskName());
        taskEntity.setStartTime(newTask.getStartTime());
        taskEntity.setEndTime(newTask.getEndTime());
        taskEntity.setSupplierId(newTask.getSupplierId());
        taskEntity.setSupplierType(newTask.getSupplierType());

        agenda.getTasks().add(taskEntity);

        agendaRepository.save(agenda);
        return true;
    }

    @Override
    @Transactional
    public boolean updateTask(Long agendaId, Long taskId, AgendaTask updatedTask) {
        agendaTaskRepository.save(mapper.map(updatedTask, AgendaTaskEntity.class));
        return true;
    }

    @Override
    @Transactional
    public boolean deleteTask(Long agendaId, Long taskId) {
//        AgendaTaskEntity task = agendaTaskRepository.findByAgendaIdAndTaskId(agendaId, taskId);

        agendaTaskRepository.deleteById(taskId);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public AgendaTask getTaskById(Long agendaId, Long taskId) {
        return agendaTaskRepository.findById(taskId)
                .map(this::convertTaskToDto)
                .orElse(null);
    }

    private Agenda convertToDto(AgendaEntity entity) {
        Agenda dto = new Agenda();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setTasks(entity.getTasks().stream()
                .map(this::convertTaskToDto)
                .toList());
        return dto;
    }

    private AgendaTask convertTaskToDto(AgendaTaskEntity entity) {
        AgendaTask dto = new AgendaTask();
        dto.setTaskId(entity.getTaskId());
        dto.setTaskName(entity.getTaskName());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setSupplierId(entity.getSupplierId());
        dto.setSupplierType(entity.getSupplierType());
        return dto;
    }
}