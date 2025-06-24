
package edu.icet.entity.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agenda")
public class AgendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)  // Ensure date is not null
    private LocalDate date;

    @Column(nullable = true)  // Ensure time is not null
    private LocalTime time;

    @OneToOne
    @JoinColumn(name = "event_id")
    private EventEntity eventId;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "agenda_id")
    private List<AgendaTaskEntity> tasks;
}
