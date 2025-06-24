package edu.icet.dto.event;

import edu.icet.util.SupplierType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaTask {
    private Long taskId;

    @NotBlank(message = "Task name is required")
    private String taskName;

    private OffsetDateTime startTime;

    private OffsetDateTime endTime;

    private Long supplierId;

    @Enumerated(EnumType.STRING)
    private SupplierType supplierType;


}