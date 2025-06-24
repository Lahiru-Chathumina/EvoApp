package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingSlotForSupplier {
    private Long bookingSlotSupplierId;

    private Long supplierId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}