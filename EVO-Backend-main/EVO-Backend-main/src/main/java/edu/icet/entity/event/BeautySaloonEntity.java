package edu.icet.entity.event;


import edu.icet.entity.supplier.SupplierEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "beauty_salon")
public class BeautySaloonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialty;

    // Add - New
    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;


}