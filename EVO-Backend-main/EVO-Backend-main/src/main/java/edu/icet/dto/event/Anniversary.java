package edu.icet.dto.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Anniversary {
    private Long eventId;
    private Long eventSummaryId;
    private Integer anniversaryYear;
    private String wifeName;
    private String husbandName;
    private String description;
}