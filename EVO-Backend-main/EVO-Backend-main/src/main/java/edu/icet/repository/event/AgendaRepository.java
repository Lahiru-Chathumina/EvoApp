package edu.icet.repository.event;

import edu.icet.entity.event.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<AgendaEntity,Long> {
}