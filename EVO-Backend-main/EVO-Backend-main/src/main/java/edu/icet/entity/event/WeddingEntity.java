package edu.icet.entity.event;

import edu.icet.util.WeddingType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wedding")
public class WeddingEntity {
    @Column(unique = true)
    private Long eventId;

    @Id
    @Column(unique = true)
    private Long eventSummaryId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WeddingType weddingType;
}