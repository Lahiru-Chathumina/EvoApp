package edu.icet.service.system.impl;

import edu.icet.dto.supplier.Venue;
import edu.icet.dto.supplier.VenueRequest;
import edu.icet.entity.supplier.VenueRequestEntity;
import edu.icet.repository.supplier.VenueRequestRepository;
import edu.icet.service.supplier.VenueService;
import edu.icet.service.system.VenueRequestService;
import edu.icet.util.EventType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class VenueRequestServiceImpl implements VenueRequestService {

    private final VenueRequestRepository venueRequestRepository;
    private final VenueService venueService;
    private ModelMapper mapper;

    @Override
    public VenueRequest save(VenueRequest venueRequest) {
        return mapper.map(venueRequestRepository.save(mapper.map(venueRequest, VenueRequestEntity.class)),VenueRequest.class);
    }

    @Override
    public Map<List<VenueRequest>,List<Venue>> getAll() {
        List<VenueRequest> venueRequestList = new ArrayList<>();
        List<Venue> venueList = new ArrayList<>();
        venueRequestRepository.findAll().forEach(venueRequestEntity ->{
            venueRequestList.add(mapper.map(venueRequestEntity, VenueRequest.class));
            venueList.add(venueService.searchVenueByID(venueRequestEntity.getVenue().getVenueId()));
        });
        return Map.of(venueRequestList,venueList);
    }

    @Override
    public Map<VenueRequest,Venue> getById(Long id) {
        VenueRequest venueRequest = mapper.map(venueRequestRepository.findById(id), VenueRequest.class);
        Venue venue = mapper.map(venueService.searchVenueByID(venueRequest.getVenue().getVenueId()), Venue.class);
        return Map.of(venueRequest,venue);
    }

    @Override
    public boolean delete(Long id) {
        venueRequestRepository.deleteById(id);
        return true;
    }

    @Override
    public VenueRequest update(VenueRequest venueRequest) {
        return this.save(venueRequest);
    }

    @Override
    public List<Venue> getAllVisibleVenues() {
        List<Venue> visibleVenues = new ArrayList<>();
        venueRequestRepository.findAll().forEach(venueRequestEntity -> {
            if (Boolean.TRUE.equals(venueRequestEntity.getStatus())){
                visibleVenues.add(venueService.searchVenueByID(venueRequestEntity.getVenue().getVenueId()));
            }
        });
        return visibleVenues;
    }

    @Override
    public List<Venue> getAllVisibleVenuesByLocation(String location) {
        List<Venue> visibleVenuesByLocation = new ArrayList<>();
        venueRequestRepository.findAll().forEach(venueRequestEntity -> {
           if(venueRequestEntity.getVenue().getLocation().equals(location)) {
               visibleVenuesByLocation.add(venueService.searchVenueByID(venueRequestEntity.getVenue().getVenueId()));
           }
        });
        return visibleVenuesByLocation;
    }

    @Override
    public List<Venue> getAllVisibleVenuesByEventType(EventType eventType) {
//        List<Venue> visibleVenuesByEventType = new ArrayList<>();
//        venueRequestRepository.findAll().forEach(venueRequestEntity -> {
//            if(venueRequestEntity.getVenue().getEventType().equals(eventType)){
//                visibleVenuesByEventType.add(venueService.findById(venueRequestEntity.getVenue().getVenueId()));
//            }
//        });
//        return visibleVenuesByEventType;
        return null;
    }
}
