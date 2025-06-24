package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingSlot {
    private Long bookingSlotId;

    private Long propertyId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}