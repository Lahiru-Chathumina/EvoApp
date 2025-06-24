package edu.icet.service.supplier;

import edu.icet.dto.supplier.Venue;
import edu.icet.util.VenueType;

import java.util.List;

public interface VenueService {
    // Venue
    List<Venue> getAllVenues();
    Venue addVenueSupplier(Venue venue);
    Boolean deleteVenueSupplier(Long venueID);
    Venue updateVenueSupplier(Venue venue);
    Venue searchVenueByID(Long venueID);
    List<Venue> findByVenueType(VenueType venueType);
}
