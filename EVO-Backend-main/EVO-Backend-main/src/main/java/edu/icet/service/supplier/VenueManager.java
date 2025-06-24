package edu.icet.service.supplier;

import edu.icet.dto.supplier.*;
import edu.icet.util.EventType;
import edu.icet.util.VenueType;

import java.util.List;
import java.util.Map;

public interface VenueManager {

    // Property
    List<Property> getAllProperties();
    Property addProperty(Property property);
    Property searchProperty(Long propertyID);
    Boolean deleteProperty(Long propertyID);
    Property updateProperty(Property property);

    // Venue
    List<Venue> getAllVenues();
    Supplier addVenueSupplier(Venue venue, Long supplierID);
    Boolean deleteVenueSupplier(Long supplierID);
    Supplier updateVenueSupplier(Venue venue);
    Venue searchVenueByID(Long venueID);
    Venue findByVenueType(VenueType venueType);

    // Hall
    List<Hall> getAllHalls();
    Property addHallProperty(Hall hall, Long propertyID);
    Hall searchHallByID(Long hallID);
    Boolean deleteHallByID(Long propertyID);
    Property updateHallProperty(Hall hall, Long propertyID);

    // Pool
    List<Pool> getAllPools();
    Property addPoolProperty(Pool pool, Long propertyID);
    Property updatePoolProperty(Pool pool, Long propertyID);
    Boolean deletePoolProperty(Long propertyID);

    // Room
    List<Room> getAllRooms();
    Property saveRoomProperty(Room room, Long propertyID);
    Room searchRoomByID(Long id);
    Boolean deleteRoomPropertyByID(Long propertyID);
    Property updateRoomProperty(Room room, Long propertyID);

    // Outdoor Area
    List<OutdoorArea> getAllOutdoorAreas();
    Property saveOutdoorAreaProperty(OutdoorArea outdoorArea, Long propertyID);
    Boolean deleteOutdoorAreaProperty(Long propertyID);
    OutdoorArea updateOutdoorAreaProperty(OutdoorArea outdoorArea, Long propertyID);

    //Property image
    List<String> getAllPropertyImage();
    Property addPropertyImage(String propertyImage, Long propertyID);
    Boolean deletePropertyImage(Long propertyImageID);
    Property updatePropertyImage(String propertyImage, Long propertyID);

    // VenueRequest
    Venue addVenueRequest(VenueRequest venueRequest, Long venueID);
    Map<List<VenueRequest>,List<Venue>> getAllVenueRequests();
    Map<VenueRequest,Venue> getVenueRequestById(Long id);
    Boolean deleteVenueRequest(Long venueRequestID);
    Venue updateVenueRequest(VenueRequest venueRequest, Long venueID);
    List<Venue> getAllVisibleVenues();
    List<Venue> getAllVisibleVenuesByLocation(String location);
    List<Venue> getAllVisibleVenuesByEventType(EventType eventType);


}
