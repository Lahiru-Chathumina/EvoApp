package edu.icet.entity.event;

import edu.icet.util.BudgetType;
import edu.icet.util.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_summary")
@Builder
public class EventSummaryEntity {
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
/*
    @JoinColumn(name = "request_id")
    private List<SupplierRequestEntity> requests;
*/
}