package edu.icet.repository.supplier;

import edu.icet.entity.supplier.BookingSlotEntity;
import edu.icet.entity.supplier.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingSlotRepository extends JpaRepository<BookingSlotEntity,Long> {

    List<BookingSlotEntity> findByPropertyAndStartTimeAndEndTime(PropertyEntity property, LocalDateTime startTime, LocalDateTime endTime);

}
