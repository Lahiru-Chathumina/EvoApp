package edu.icet.service.system.impl;

import edu.icet.dto.supplier.Location;
import edu.icet.entity.supplier.LocationEntity;
import edu.icet.repository.system.LocationRepository;
import edu.icet.service.system.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
	private final LocationRepository locationRepository;
	private final ModelMapper mapper;

	@Override
	public List<Location> getAll () {
		return this.locationRepository.findAll().stream().map(locationEntity -> this.mapper.map(locationEntity, Location.class)).toList();
	}
}
