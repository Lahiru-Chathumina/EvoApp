package edu.icet.entity.event;

import edu.icet.entity.supplier.ProfilePackageEntity;
import edu.icet.entity.supplier.SupplierEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Builder
public class EventSummarySuppliersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_summary_id")
    private EventSummaryEntity eventSummary;

    @OneToMany
    @JoinColumn(name = "supplier_summary_id")
    private List<SupplierEntity> suppliers;

    @OneToMany
    @JoinColumn(name = "package_summary_id")
    private List<ProfilePackageEntity> packages;
}
