package edu.icet.entity.event;

import edu.icet.util.BudgetType;
import edu.icet.util.EventStatusType;
import edu.icet.util.EventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    private Long venueId;
    private String location;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(nullable = false)
    private Integer headCount;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BudgetType budgetType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatusType eventStatus;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Transient
    private AnniversaryEntity anniversary;

    @Transient
    private BirthdayPartyEntity birthdayParty;

    @Transient
    private GetTogetherEntity getTogether;

    @Transient
    private WeddingEntity wedding;
}