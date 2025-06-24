package edu.icet.dto.supplier;

import edu.icet.util.VenueType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private Long venueId;

    private Long capacity;

    private Location location;

    @Enumerated(EnumType.STRING)
    private VenueType venueType;

    private List<Property> properties;

    private List<VenueRequest> requests;

    private Supplier supplier;
}