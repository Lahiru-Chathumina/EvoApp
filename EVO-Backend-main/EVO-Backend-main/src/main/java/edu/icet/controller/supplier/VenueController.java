package edu.icet.controller.supplier;

import edu.icet.dto.supplier.*;
import edu.icet.service.supplier.VenueManager;
import edu.icet.util.EventType;
import edu.icet.util.VenueType;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/supplier/venue")
@RequiredArgsConstructor
public class VenueController {
    private final VenueManager service;

    // Property endpoints

    @GetMapping("/property")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = service.getAllProperties();

        return properties.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(properties);
    }

    @PostMapping("/property")
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        return ResponseEntity.ok(service.addProperty(property));
    }

    @GetMapping("/property/{propertyID}")
    public ResponseEntity<Property> searchProperty(@PathVariable Long propertyID) {
        return ResponseEntity.ok(service.searchProperty(propertyID));
    }

    @DeleteMapping("/property/{propertyID}")
    public ResponseEntity<Boolean> deleteProperty(@PathVariable Long propertyID) {
        return ResponseEntity.ok(service.deleteProperty(propertyID));
    }

    @PutMapping("/property")
    public ResponseEntity<Property> updateProperty(@RequestBody Property property) {
        return ResponseEntity.ok(service.updateProperty(property));
    }

    // Venue endpoints

    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues() {
        List<Venue> venues = service.getAllVenues();

        return venues.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(venues);
    }

    @PostMapping("/{supplierID}")
    public ResponseEntity<Supplier> addVenueSupplier(@RequestBody Venue venue, @PathVariable Long supplierID) {
        return ResponseEntity.ok(service.addVenueSupplier(venue, supplierID));
    }

    @DeleteMapping("/{supplierID}")
    public ResponseEntity<Boolean> deleteVenueSupplier(@PathVariable Long supplierID) {
        return ResponseEntity.ok(service.deleteVenueSupplier(supplierID));
    }

    @PutMapping
    public ResponseEntity<Supplier> updateVenueSupplier(@RequestBody Venue venue) {
        return ResponseEntity.ok(service.updateVenueSupplier(venue));
    }

    @GetMapping("/{venueID}")
    public ResponseEntity<Venue> searchVenueByID(@PathVariable Long venueID) {
        return ResponseEntity.ok(service.searchVenueByID(venueID));
    }

    @GetMapping("/type")
    public ResponseEntity<Venue> findByVenueType(@RequestParam VenueType venueType) {
        return ResponseEntity.ok(service.findByVenueType(venueType));
    }

    // Hall endpoints

    @GetMapping("/hall")
    public ResponseEntity<List<Hall>> getAllHalls() {
        List<Hall> halls = service.getAllHalls();

        return halls.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(halls);
    }

    @PostMapping("/property/{propertyID}/hall")
    public ResponseEntity<Property> addHallProperty(@RequestBody Hall hall, @PathVariable Long propertyID) {
        return ResponseEntity.ok(service.addHallProperty(hall, propertyID));
    }

    @GetMapping("/hall/{hallID}")
    public ResponseEntity<Hall> searchHallByID(@PathVariable Long hallID) {
        return ResponseEntity.ok(service.searchHallByID(hallID));
    }

    @DeleteMapping("/property/{propertyID}/hall")
    public ResponseEntity<Boolean> deleteHallByID(@PathVariable Long propertyID) {
        return ResponseEntity.ok(service.deleteHallByID(propertyID));
    }

    @PutMapping("/property/{propertyID}/hall")
    public ResponseEntity<Property> updateHallProperty(@RequestBody Hall hall, @PathVariable Long propertyID) {
        return ResponseEntity.ok(service.updateHallProperty(hall, propertyID));
    }

    // Pool endpoints

    @GetMapping("/pool")
    public ResponseEntity<List<Pool>> getAllPools() {
        List<Pool> pools = service.getAllPools();

        return pools.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(pools);
    }

    @PostMapping("/property/{propertyID}/pool")
    public ResponseEntity<Property> addPoolProperty(@RequestBody Pool pool, @PathVariable Long propertyID) {
        return ResponseEntity.ok(service.addPoolProperty(pool, propertyID));
    }

    @PutMapping("/property/{propertyID}/pool")
    public ResponseEntity<Property> updatePoolProperty(@RequestBody Pool pool, @PathVariable Long propertyID) {
        return ResponseEntity.ok(service.updatePoolProperty(pool, propertyID));
    }

    @DeleteMapping("/property/{propertyID}/pool")
    public ResponseEntity<Boolean> deletePoolProperty(@PathVariable Long propertyID) {
        return ResponseEntity.ok(service.deletePoolProperty(propertyID));
    }

    // Room endpoints

    @GetMapping("/room")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = service.getAllRooms();

        return rooms.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(rooms);
    }

    @PostMapping("/property/{propertyID}/room")
    public ResponseEntity<Property> saveRoomProperty(@RequestBody Room room, @PathVariable Long propertyID) {
        return ResponseEntity.ok(service.saveRoomProperty(room, propertyID));
    }

    @GetMapping("/room/{roomID}")
    public ResponseEntity<Room> searchRoomByID(@PathVariable Long roomID) {
        return ResponseEntity.ok(service.searchRoomByID(roomID));
    }

    @DeleteMapping("/property/{propertyID}/room")
    public ResponseEntity<Boolean> deleteRoomPropertyByID(@PathVariable Long propertyID) {
        return ResponseEntity.ok(service.deleteRoomPropertyByID(propertyID));
    }

    @PutMapping("/property/{propertyID}/room")
    public ResponseEntity<Property> updateRoomProperty(@RequestBody Room room, @PathVariable Long propertyID) {
        return ResponseEntity.ok(service.updateRoomProperty(room, propertyID));
    }

    // Outdoor Area endpoints

    @GetMapping("/outdoor-area")
    public ResponseEntity<List<OutdoorArea>> getAllOutdoorAreas() {
        List<OutdoorArea> outdoorAreas = service.getAllOutdoorAreas();

        return outdoorAreas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(outdoorAreas);
    }

    @PostMapping("/property/{propertyID}/outdoor-area")
    public ResponseEntity<Property> saveOutdoorAreaProperty(@RequestBody OutdoorArea outdoorArea, @PathVariable Long propertyID) {
        return ResponseEntity.ok(service.saveOutdoorAreaProperty(outdoorArea, propertyID));
    }

    @DeleteMapping("/property/{propertyID}/outdoor-area")
    public ResponseEntity<Boolean> deleteOutdoorAreaProperty(@PathVariable Long propertyID) {
        return ResponseEntity.ok(service.deleteOutdoorAreaProperty(propertyID));
    }

    @PutMapping("/property/{propertyID}/outdoor-area")
    public ResponseEntity<OutdoorArea> updateOutdoorAreaProperty(@RequestBody OutdoorArea outdoorArea, @PathVariable Long propertyID) {
        return ResponseEntity.ok(service.updateOutdoorAreaProperty(outdoorArea, propertyID));
    }

    // Venue Request endpoints

    @GetMapping("/request")
    public ResponseEntity<Map<List<VenueRequest>, List<Venue>>> getAllVenueRequests() {
        return ResponseEntity.ok(service.getAllVenueRequests());
    }

    @GetMapping("/request/{venueRequestID}")
    public ResponseEntity<Map<VenueRequest, Venue>> getVenueRequestById(@PathVariable Long venueRequestID) {
        return ResponseEntity.ok(service.getVenueRequestById(venueRequestID));
    }

    @PostMapping("/{venueID}/request")
    public ResponseEntity<Venue> addVenueRequest(@RequestBody VenueRequest venueRequest, @PathVariable Long venueID) {
        return ResponseEntity.ok(service.addVenueRequest(venueRequest, venueID));
    }

    @PutMapping("/{venueID}/request")
    public ResponseEntity<Venue> updateVenueRequest(@RequestBody VenueRequest venueRequest, @PathVariable Long venueID) {
        return ResponseEntity.ok(service.updateVenueRequest(venueRequest, venueID));
    }

    @DeleteMapping("/request/{venueRequestID}")
    public ResponseEntity<Boolean> deleteVenueRequest(@PathVariable Long venueRequestID) {
        return ResponseEntity.ok(service.deleteVenueRequest(venueRequestID));
    }

    // Visible Venues endpoints

    @GetMapping("/request/visible")
    public ResponseEntity<List<Venue>> getAllVisibleVenues() {
        List<Venue> venues = service.getAllVisibleVenues();

        return venues.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(venues);
    }

    @GetMapping("/request/visible/location")
    public ResponseEntity<List<Venue>> getAllVisibleVenuesByLocation(@RequestParam String location) {
        List<Venue> venues = service.getAllVisibleVenuesByLocation(location);

        return venues.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(venues);
    }

    @GetMapping("/request/visible/event-type")
    @Operation(summary = "Returns all visible venues by event type")
    public ResponseEntity<List<Venue>> getAllVisibleVenuesByEventType(@RequestParam EventType eventType) {
        List<Venue> venues = service.getAllVisibleVenuesByEventType(eventType);

        return venues.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(venues);
    }
}