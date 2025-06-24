package edu.icet.entity.admin;

import edu.icet.util.AuditActionType;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AuditLog")
public class AuditLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    @Column(nullable = false)
    private String timestamp;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditActionType type;
}
