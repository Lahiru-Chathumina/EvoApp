package edu.icet.service.customer;

import edu.icet.dto.event.Agenda;
import edu.icet.dto.event.AgendaTask;

import java.util.List;

public interface AgendaService {
    boolean create(Agenda agenda);
    List<Agenda> getAll();
    boolean update(Agenda agenda);
    boolean delete(Long id);
    Agenda getById(Long id);
    boolean addTaskToAgenda(Long agendaId, AgendaTask newTask);
    boolean updateTask(Long agendaId, Long taskId, AgendaTask updatedTask);
    boolean deleteTask(Long agendaId, Long taskId);
    AgendaTask getTaskById(Long agendaId, Long taskId);
}