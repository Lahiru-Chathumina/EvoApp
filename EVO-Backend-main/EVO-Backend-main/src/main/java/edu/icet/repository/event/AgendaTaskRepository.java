package edu.icet.repository.event;

import edu.icet.entity.event.AgendaTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaTaskRepository extends JpaRepository<AgendaTaskEntity, Long> {
//    @Query("SELECT t FROM AgendaTaskEntity t WHERE t.agenda.id = :agendaId AND t.taskId = :taskId")
//    AgendaTaskEntity findByAgendaIdAndTaskId(@Param("agendaId") Long agendaId, @Param("taskId") Long taskId);
}