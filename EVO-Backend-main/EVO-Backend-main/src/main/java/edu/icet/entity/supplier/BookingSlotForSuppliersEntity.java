package edu.icet.entity.supplier;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bookingSlot-suppliers")
public class BookingSlotForSuppliersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingSlotSupplierId;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}