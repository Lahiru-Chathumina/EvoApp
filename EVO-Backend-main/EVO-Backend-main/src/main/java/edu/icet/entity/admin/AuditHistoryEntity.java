package edu.icet.entity.admin;

import edu.icet.util.AuditActionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AuditHistory")
public class AuditHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reasonForChange;

    @Column(nullable = false)
    private Timestamp timestamp;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuditActionType actionType;

    @Column(nullable = false)
    private String affectedEntity;

    @Column(nullable = false)
    private String newData;

    @Column(nullable = false)
    private String oldData;
}
