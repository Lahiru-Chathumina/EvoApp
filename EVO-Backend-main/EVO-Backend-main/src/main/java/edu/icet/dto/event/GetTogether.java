
package edu.icet.dto.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTogether {
    private Long eventId;
    private Long eventSummaryId;
    private String description;
    private String title;
}
