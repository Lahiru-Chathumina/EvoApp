package edu.icet.entity.admin;

import edu.icet.entity.event.EventEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "audit_report")
@Entity
public class AuditReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

//    @OneToOne
//    @JoinColumn(name = "event_id")
//    private EventEntity event; TODO: Unsure relationship

    private String timestamp;

    private String action;

    private String data;

}
