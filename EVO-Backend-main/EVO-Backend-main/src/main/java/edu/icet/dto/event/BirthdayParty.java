package edu.icet.dto.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BirthdayParty {
    private Long eventId;
    private Long eventSummaryId;
    private String ownerName;
}