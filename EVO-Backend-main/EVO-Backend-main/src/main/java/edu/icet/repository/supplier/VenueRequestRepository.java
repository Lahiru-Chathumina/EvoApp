package edu.icet.repository.supplier;

import edu.icet.entity.supplier.SupplierEntity;
import edu.icet.entity.supplier.VenueEntity;
import edu.icet.entity.supplier.VenueRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VenueRequestRepository extends JpaRepository<VenueRequestEntity, Long> {
    List<VenueRequestEntity> findBySupplier(SupplierEntity supplier);
    List<VenueRequestEntity> findByVenue(VenueEntity venue);
    List<VenueRequestEntity> findByStatus(Boolean status);
}