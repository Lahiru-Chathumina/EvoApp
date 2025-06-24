package edu.icet.dto.admin;

import edu.icet.util.AuditActionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuditLog {
    @NotNull(message = "Request ID is required")
    private Long logId;
    @NotNull(message = "Timestamp is required")
    private String timestamp;
    private AuditActionType type;
}
