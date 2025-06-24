package edu.icet.entity.supplier;

import edu.icet.util.VenueType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venue")

public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;

    private Long capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VenueType venueType;

    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @OneToMany
    @JoinColumn(name = "venue_id")
    private List<PropertyEntity> properties;

    @OneToMany
    @JoinColumn(name = "venue_id")
    private List<VenueRequestEntity> requests;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;

}
