package edu.icet.controller.system;

import edu.icet.dto.supplier.Location;
import edu.icet.service.system.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/location")
public class LocationController {
	private final LocationService locationService;

	@GetMapping("/all")
	public ResponseEntity<List<Location>> getAllLocations () {
		return ResponseEntity.ok(this.locationService.getAll());
	}
}
