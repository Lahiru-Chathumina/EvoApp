package edu.icet.service.supplier.impl;

import edu.icet.dto.supplier.*;
import edu.icet.repository.supplier.VenueRepository;
import edu.icet.service.supplier.*;
import edu.icet.service.system.VenueRequestService;
import edu.icet.util.EventType;
import edu.icet.util.VenueType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service

public class VenueManagerImpl implements VenueManager {
    private final VenueRepository repository;
    private final ModelMapper mapper;

    final PropertyService propertyService;
    final HallService hallService;
    final PoolService poolService;
    final RoomService roomService;
    final OutdoorAreaService outdoorAreaService;
    final VenueRequestService venueRequestService;

    @Override
    public List<Property> getAllProperties() {
        return null;
    }

    @Override
    public Property addProperty(Property property) {
        return null;
    }

    @Override
    public Property searchProperty(Long propertyID) {
        return null;
    }

    @Override
    public Boolean deleteProperty(Long propertyID) {
        return false;
    }

    @Override
    public Property updateProperty(Property property) {
        return null;
    }

    @Override
    public List<Venue> getAllVenues() {
        return null;
    }

    @Override
    public Supplier addVenueSupplier(Venue venue, Long supplierID) {
        return null;
    }

    @Override
    public Boolean deleteVenueSupplier(Long supplierID) {
        return false;
    }

    @Override
    public Supplier updateVenueSupplier(Venue venue) {
        return null;
    }

    @Override
    public Venue searchVenueByID(Long venueID) {
        return null;
    }

    @Override
    public Venue findByVenueType(VenueType venueType) {
        return null;
    }


    @Override
    public List<Hall> getAllHalls() {
        return null;
    }

    @Override
    public Property addHallProperty(Hall hall, Long propertyID) {
        return null;
    }

    @Override
    public Hall searchHallByID(Long hallID) {
        return null;
    }

    @Override
    public Boolean deleteHallByID(Long propertyID) {
        return false;
    }

    @Override
    public Property updateHallProperty(Hall hall, Long propertyID) {
        return null;
    }

    @Override
    public List<Pool> getAllPools() {
        return null;
    }

    @Override
    public Property addPoolProperty(Pool pool, Long propertyID) {
        return null;
    }

    @Override
    public Property updatePoolProperty(Pool pool, Long propertyID) {
        return null;
    }

    @Override
    public Boolean deletePoolProperty(Long propertyID) {
        return false;
    }

    @Override
    public List<Room> getAllRooms() {
        return null;
    }

    @Override
    public Property saveRoomProperty(Room room, Long propertyID) {
        return null;
    }

    @Override
    public Room searchRoomByID(Long id) {
        return null;
    }

    @Override
    public Boolean deleteRoomPropertyByID(Long propertyID) {
        return false;
    }

    @Override
    public Property updateRoomProperty(Room room, Long propertyID) {
        return null;
    }

    @Override
    public List<OutdoorArea> getAllOutdoorAreas() {
        return null;
    }

    @Override
    public Property saveOutdoorAreaProperty(OutdoorArea outdoorArea, Long propertyID) {
        return null;
    }

    @Override
    public Boolean deleteOutdoorAreaProperty(Long propertyID) {
        return false;
    }

    @Override
    public OutdoorArea updateOutdoorAreaProperty(OutdoorArea outdoorArea, Long propertyID) {
        return null;
    }

    @Override
    public List<String> getAllPropertyImage() {
        return List.of();
    }

    @Override
    public Property addPropertyImage(String propertyImage, Long propertyID) {
        return null;
    }


    @Override
    public Boolean deletePropertyImage(Long propertyImageID) {
        return false;
    }

    @Override
    public Property updatePropertyImage(String propertyImage, Long propertyID) {
        return null;
    }

    @Override
    public Venue addVenueRequest(VenueRequest venueRequest, Long venueID) {
        return null;
    }

    @Override
    public Map<List<VenueRequest>, List<Venue>> getAllVenueRequests() {
        return null;
    }

    @Override
    public Map<VenueRequest, Venue> getVenueRequestById(Long id) {
        return null;
    }

    @Override
    public Boolean deleteVenueRequest(Long venueRequestID) {
        return false;
    }

    @Override
    public Venue updateVenueRequest(VenueRequest venueRequest, Long venueID) {
        return null;
    }

    @Override
    public List<Venue> getAllVisibleVenues() {
        return null;
    }

    @Override
    public List<Venue> getAllVisibleVenuesByLocation(String location) {
        return null;
    }

    @Override
    public List<Venue> getAllVisibleVenuesByEventType(EventType eventType) {
        return null;
    }
}
