package edu.icet.dto.event;

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
public class Event {
    private Long id;
    private Long userId;
    private Long venueId;
    private String location;
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private EventType eventType;
    private Integer headCount;
    private Double totalPrice;
    private BudgetType budgetType;
    private EventStatusType eventStatus;
    private Anniversary anniversary;
    private BirthdayParty birthdayParty;
    private GetTogether getTogether;
    private Wedding wedding;
}