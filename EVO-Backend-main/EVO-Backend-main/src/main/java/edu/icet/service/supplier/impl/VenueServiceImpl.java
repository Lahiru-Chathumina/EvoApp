package edu.icet.service.supplier.impl;

import edu.icet.dto.supplier.Venue;
import edu.icet.entity.supplier.VenueEntity;
import edu.icet.repository.supplier.VenueRepository;
import edu.icet.service.supplier.VenueService;
import edu.icet.util.VenueType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Venue> getAllVenues() {
        return repository.findAll().stream().map(venue -> mapper.map(venue, Venue.class)).toList();
    }

    @Override
    public Venue addVenueSupplier(Venue venue) {
        return mapper.map(repository.save(mapper.map(venue, VenueEntity.class)), Venue.class);
    }

    @Override
    public Boolean deleteVenueSupplier(Long venueID) {
        if (repository.existsById(venueID)) {
            repository.deleteById(venueID);
            return true;
        }

        throw new IllegalArgumentException("Venue does not exist!");
    }

    @Override
    public Venue updateVenueSupplier(Venue venue) {
        if (repository.existsById(venue.getVenueId())) {
            return mapper.map(repository.save(mapper.map(venue, VenueEntity.class)), Venue.class);
        }

        throw new IllegalArgumentException("Venue does not exist!");
    }

    @Override
    public Venue searchVenueByID(Long venueID) {
        VenueEntity venueEntity = repository.findById(venueID).orElse(null);

        return venueEntity != null ? mapper.map(venueEntity, Venue.class) : null;
    }

    @Override
    public List<Venue> findByVenueType(VenueType venueType) {
        return repository.findAllByVenueType(venueType)
                .stream()
                .map(venueEntity -> mapper.map(venueEntity, Venue.class))
                .toList();
    }
}
