package edu.icet.entity.supplier;

import edu.icet.util.HallAvailabilityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hall")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long hallId;

    @Column(name = "count",nullable = false)
    @Min(1)
    private Integer count;

    @Column(name = "availability",nullable = false)
    @Enumerated(EnumType.STRING)
    private HallAvailabilityType availability;
}
