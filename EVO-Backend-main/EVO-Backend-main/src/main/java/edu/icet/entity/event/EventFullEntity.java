package edu.icet.entity.event;

import edu.icet.entity.customer.UserEntity;
import edu.icet.entity.supplier.VenueEntity;
import edu.icet.util.BudgetType;
import edu.icet.util.EventStatusType;
import edu.icet.util.EventType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventFullEntity {
    private Long id;
    private UserEntity user;
    private VenueEntity venue;
    private String location;
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private EventType eventType;
    private Integer headCount;
    private Double totalPrice;
    private BudgetType budgetType;
    private EventStatusType eventStatus;
    private AnniversaryEntity anniversary;
    private BirthdayPartyEntity birthdayParty;
    private GetTogetherEntity getTogether;
    private WeddingEntity wedding;
}