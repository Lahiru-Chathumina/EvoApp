package edu.icet.service.supplier;

import edu.icet.dto.supplier.BookingSlot;
import edu.icet.dto.supplier.Property;

import java.time.LocalDateTime;
import java.util.List;

public interface   BookingSlotService {

    List<BookingSlot> getAll();

    BookingSlot save(BookingSlot bookingSlot);

    Boolean delete(Long id);

    BookingSlot update(BookingSlot bookingSlot);

    List<BookingSlot> search(Property property, LocalDateTime startTime, LocalDateTime endTime);
}

