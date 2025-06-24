package edu.icet.entity.supplier;

import edu.icet.util.SupplierCategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;

    private String imageURL;

    private String description;

    @Enumerated(EnumType.STRING)
    private SupplierCategoryType supplierCategory;
}
