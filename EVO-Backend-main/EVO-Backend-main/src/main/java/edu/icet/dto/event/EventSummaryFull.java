package edu.icet.dto.event;

import edu.icet.dto.customer.User;
import edu.icet.dto.supplier.Venue;
import edu.icet.util.BudgetType;
import edu.icet.util.EventType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventSummaryFull {
    private Long id;
    private User user;
    private Venue venue;
    private String location;
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private EventType eventType;
    private Integer headCount;
    private Double totalPrice;
    private BudgetType budgetType;
    private Anniversary anniversary;
    private BirthdayParty birthdayParty;
    private GetTogether getTogether;
    private Wedding wedding;
}