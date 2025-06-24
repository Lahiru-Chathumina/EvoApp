package edu.icet.entity.event;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "birthday_party")
public class BirthdayPartyEntity {
    @Column(unique = true)
    private Long eventId;

    @Id
    @Column(nullable = false, unique = true)
    private Long eventSummaryId;

    @Column(nullable = false)
    private String ownerName;
}