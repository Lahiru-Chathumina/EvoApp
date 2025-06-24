package edu.icet.repository.system;

import edu.icet.dto.supplier.Location;
import edu.icet.entity.supplier.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
