package edu.icet.repository.supplier;

import edu.icet.entity.supplier.VenueEntity;
import edu.icet.util.VenueType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepository extends JpaRepository<VenueEntity,Long> {
    List<VenueEntity> findAllByVenueType(VenueType venueType);
}
