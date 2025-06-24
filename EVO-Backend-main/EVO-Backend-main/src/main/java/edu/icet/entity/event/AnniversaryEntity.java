package edu.icet.entity.event;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anniversary")
public class AnniversaryEntity {
    @Column(unique = true)
    private Long eventId;

    @Id
    @Column(nullable = false, unique = true)
    private Long eventSummaryId;

    @Column(nullable = false)
    private Integer anniversaryYear;

    @Column(nullable = false)
    private String wifeName;

    @Column(nullable = false)
    private String husbandName;

    private String description;
}