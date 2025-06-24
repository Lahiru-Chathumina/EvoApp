package edu.icet.entity.supplier;

import edu.icet.dto.supplier.Venue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "venue_request")
public class VenueRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @OneToOne
    @JoinColumn(name = "venue_id")
    private VenueEntity venue;

    @Column(name = "created_datetime",nullable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private Boolean status;
}
