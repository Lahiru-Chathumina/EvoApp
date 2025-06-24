package edu.icet.service.system;

import edu.icet.dto.supplier.Venue;
import edu.icet.dto.supplier.VenueRequest;
import edu.icet.util.EventType;

import java.util.List;
import java.util.Map;


public interface VenueRequestService {
    VenueRequest save(VenueRequest venueRequest);
    Map<List<VenueRequest>,List<Venue>> getAll();
    Map<VenueRequest,Venue> getById(Long id);
    boolean delete(Long id);
    VenueRequest update(VenueRequest venueRequest);
    List<Venue> getAllVisibleVenues();
    List<Venue> getAllVisibleVenuesByLocation(String location);
    List<Venue> getAllVisibleVenuesByEventType(EventType eventType);


}
