package edu.icet.repository.supplier;

import edu.icet.entity.supplier.HallEntity;
import edu.icet.entity.supplier.PropertyEntity;
import edu.icet.util.HallAvailabilityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<HallEntity,Long> {
    List<HallEntity> findByCountGreaterThanEqual(Integer count);
    List<HallEntity> findByAvailability(HallAvailabilityType availability);

}
