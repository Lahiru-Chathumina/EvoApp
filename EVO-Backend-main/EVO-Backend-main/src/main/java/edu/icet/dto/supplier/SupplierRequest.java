package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {
    private Long supplierRequestId;

    private LocalDateTime requestDate;

    private LocalDateTime dueDateTime;

    private Long supplierId;

    private String requestStatus;

    private Long customerId;

    private Long selectedPackageId;


    private Long locationId;
}