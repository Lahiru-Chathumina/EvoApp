package edu.icet.entity.supplier;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photographer")
public class PhotographerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photographerId;

    private String award;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;
}
