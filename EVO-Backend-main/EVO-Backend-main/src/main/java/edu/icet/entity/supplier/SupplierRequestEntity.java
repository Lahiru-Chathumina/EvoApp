package edu.icet.entity.supplier;

import edu.icet.entity.customer.CustomerEntity;
import edu.icet.util.SupplerRequestStatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SupplierRequest")

public class SupplierRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime requestDate;

    private LocalDateTime dueDateTime;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;

    @Enumerated(EnumType.STRING)
    private SupplerRequestStatusType requestStatus;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "profile_package_id")
    private ProfilePackageEntity selectedPackage;


    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;
}
