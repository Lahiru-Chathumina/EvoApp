package edu.icet.dto.event;

import edu.icet.util.WeddingType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wedding {
    private Long eventId;
    private Long eventSummaryId;
    private WeddingType weddingType;
}