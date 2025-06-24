package edu.icet.dto.admin;

import edu.icet.util.AuditActionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditHistory {

    private Long id;

    @NotNull(message = "reason cannot be null")
    private String reasonForChange;

    @NotNull(message = "timeStamp cannot be null")
    private Timestamp timestamp;

    @NotNull(message = "actionType cannot be null")
    private AuditActionType actionType;

    @NotNull(message = "affectedEntity cannot be null")
    private String affectedEntity;

    @NotNull(message = "newData cannot be null")
    private String newData;

    @NotNull(message = "oldData cannot be null")
    private String oldData;
}
