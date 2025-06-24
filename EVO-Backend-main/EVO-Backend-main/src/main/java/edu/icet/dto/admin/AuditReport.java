package edu.icet.dto.admin;

import edu.icet.dto.event.Event;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "audit_report")
public class AuditReport {

    private Long reportId;

//    private Event event; TODO: Unsure relationship

    private String timestamp;

    private String action;

    private String data;

}
