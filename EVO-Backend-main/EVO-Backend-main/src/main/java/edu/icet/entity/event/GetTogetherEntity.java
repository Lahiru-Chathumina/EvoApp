package edu.icet.entity.event;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "get_together")
public class GetTogetherEntity {
    @Column(unique = true)
    private Long eventId;

    @Id
    @Column(nullable = false, unique = true)
    private Long eventSummaryId;

    private String description;

    @Column(nullable = false)
    private String title;
}