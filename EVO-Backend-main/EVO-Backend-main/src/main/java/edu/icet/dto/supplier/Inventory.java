package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private Long inventoryId;

    private Long supplierId;

    private String imageURL;

    private String description;

    private String supplierCategory;
}